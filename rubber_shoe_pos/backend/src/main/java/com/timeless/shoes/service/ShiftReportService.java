package com.timeless.shoes.service;

import com.timeless.shoes.dto.ShiftDto;
import com.timeless.shoes.entity.CashierShift;
import com.timeless.shoes.entity.User;
import com.timeless.shoes.repository.CashierShiftRepository;
import com.timeless.shoes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftReportService {

    private final CashierShiftRepository shiftRepository;
    private final UserRepository userRepository;

    /**
     * Get all shifts (manager access)
     */
    public List<ShiftDto> getAllShifts() {
        List<CashierShift> shifts = shiftRepository.findAll();
        return shifts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Get all shifts for a specific date
     */
    public List<ShiftDto> getShiftsByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        List<CashierShift> shifts = shiftRepository.findByOpenedAtBetween(startOfDay, endOfDay);
        return shifts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Get all shifts for a specific cashier
     */
    public List<ShiftDto> getShiftsByCashier(Long cashierId) {
        User cashier = userRepository.findById(cashierId)
                .orElseThrow(() -> new RuntimeException("Cashier not found."));
        List<CashierShift> shifts = shiftRepository.findByCashier(cashier);
        return shifts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Calculate cash variance for a shift
     */
    public Double calculateCashVariance(Long shiftId) {
        CashierShift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new RuntimeException("Shift not found."));
        if (shift.getClosingBalance() == null || shift.getSystemTotal() == null) {
            return null;
        }
        return shift.getClosingBalance() - shift.getSystemTotal();
    }

    /**
     * Get a single shift by ID
     */
    public ShiftDto getShiftById(Long shiftId) {
        CashierShift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new RuntimeException("Shift not found."));
        return mapToDto(shift);
    }

    /**
     * Map CashierShift to ShiftDto
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
          }
