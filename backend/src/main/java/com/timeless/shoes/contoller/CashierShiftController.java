package com.timeless.shoes.controller;

import com.timeless.shoes.dto.CloseShiftRequest;
import com.timeless.shoes.dto.OpenShiftRequest;
import com.timeless.shoes.dto.ShiftDto;
import com.timeless.shoes.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/shifts")
@RequiredArgsConstructor
@PreAuthorize("hasRole('CASHIER')") // Only cashiers can access these endpoints
public class CashierShiftController {

    private final ShiftService shiftService;

    /**
     * Open a new shift for the current cashier
     */
    @PostMapping("/open")
    public ResponseEntity<ShiftDto> openShift(@RequestBody OpenShiftRequest request) {
        ShiftDto shift = shiftService.openShift(request);
        return ResponseEntity.ok(shift);
    }

    /**
     * Close the current shift
     */
    @PostMapping("/close")
    public ResponseEntity<ShiftDto> closeShift(@RequestBody CloseShiftRequest request) {
        ShiftDto shift = shiftService.closeShift(request.getShiftId());
        return ResponseEntity.ok(shift);
    }

    /**
     * Get the active shift for the currently logged-in cashier
     */
    @GetMapping("/active")
    public ResponseEntity<ShiftDto> getActiveShift() {
        Optional<ShiftDto> activeShift = shiftService.getActiveShiftForCurrentCashier();
        return activeShift
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
  }
