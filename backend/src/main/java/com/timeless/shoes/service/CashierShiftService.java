package com.timeless.shoes.service;

import com.timeless.shoes.dto.CloseShiftRequest;
import com.timeless.shoes.dto.OpenShiftRequest;
import com.timeless.shoes.dto.ShiftDto;
import com.timeless.shoes.entity.CashierShift;
import com.timeless.shoes.entity.Order;
import com.timeless.shoes.entity.User;
import com.timeless.shoes.repository.CashierShiftRepository;
import com.timeless.shoes.repository.OrderRepository;
import com.timeless.shoes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CashierShiftService {

    private final CashierShiftRepository shiftRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    /**
     * Open a new shift for the currently logged-in cashier
     */
    public ShiftDto openShift(OpenShiftRequest request) {
        User cashier = getCurrentUser();

        // Check if cashier already has an active shift
        Optional<CashierShift> activeShift = shiftRepository.findByCashierAndClosedAtIsNull(cashier);
        if (activeShift.isPresent()) {
            throw new RuntimeException("There is already an active shift for this cashier.");
        }

        CashierShift shift = CashierShift.builder()
                .cashier(cashier)
                .openedAt(LocalDateTime.now())
                .openingBalance(request.getOpeningBalance())
                .systemTotal(0.0)
                .build();

        shiftRepository.save(shift);

        return mapToDto(shift);
    }

    /**
     * Close an existing shift
     */
    public ShiftDto closeShift(Long shiftId) {
        CashierShift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new RuntimeException("Shift not found."));

        if (shift.getClosedAt() != null) {
            throw new RuntimeException("Shift is already closed.");
        }

        // Calculate system total
        List<Order> orders = orderRepository.findByShift(shift);
        double systemTotal = orders.stream().mapToDouble(Order::getAmount).sum();
        shift.setSystemTotal(systemTotal);

        // Here, closingBalance should be provided by cashier frontend
        if (shift.getClosingBalance() == null) {
            throw new RuntimeException("Closing balance not set.");
        }

        shift.setClosedAt(LocalDateTime.now());
        shiftRepository.save(shift);

        return mapToDto(shift);
    }

    /**
     * Get the currently active shift for the logged-in cashier
     */
    public Optional<ShiftDto> getActiveShift() {
        User cashier = getCurrentUser();
        return shiftRepository.findByCashierAndClosedAtIsNull(cashier)
                .map(this::mapToDto);
    }

    /**
     * Map CashierShift entity to DTO
     */
    private ShiftDto mapToDto(CashierShift shift) {
        ShiftDto dto = new ShiftDto();
        dto.setId(shift.getId());
        dto.setCashierUsername(shift.getCashier().getUsername());
        dto.setOpenedAt(shift.getOpenedAt());
        dto.setClosedAt(shift.getClosedAt());
        dto.setOpeningBalance(shift.getOpeningBalance());
        dto.setClosingBalance(shift.getClosingBalance());
        dto.setSystemTotal(shift.getSystemTotal());
        return dto;
    }

    /**
     * Get currently logged-in user from Spring Security context
     */
    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found."));
    }
          }
