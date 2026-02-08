package com.timeless.shoes.controller;

import com.timeless.shoes.dto.ShiftDto;
import com.timeless.shoes.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/manager/shifts")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MANAGER')") // Only managers can access
public class ManagerShiftController {

    private final ShiftService shiftService;

    /**
     * Get all shifts
     * GET /api/manager/shifts
     */
    @GetMapping
    public ResponseEntity<List<ShiftDto>> getAllShifts() {
        List<ShiftDto> shifts = shiftService.getAllShifts();
        return ResponseEntity.ok(shifts);
    }

    /**
     * Get shifts for a specific date
     * GET /api/manager/shifts/date?date=YYYY-MM-DD
     */
    @GetMapping("/date")
    public ResponseEntity<List<ShiftDto>> getShiftsByDate(@RequestParam LocalDate date) {
        List<ShiftDto> shifts = shiftService.getShiftsByDate(date);
        return ResponseEntity.ok(shifts);
    }

    /**
     * Get cash variance for a specific shift
     * GET /api/manager/shifts/{shiftId}/variance
     */
    @GetMapping("/{shiftId}/variance")
    public ResponseEntity<Double> getCashVariance(@PathVariable Long shiftId) {
        double variance = shiftService.calculateCashVariance(shiftId);
        return ResponseEntity.ok(variance);
    }

    /**
     * Get a specific shift by ID
     * GET /api/manager/shifts/{shiftId}
     */
    @GetMapping("/{shiftId}")
    public ResponseEntity<ShiftDto> getShiftById(@PathVariable Long shiftId) {
        ShiftDto shift = shiftService.getShiftById(shiftId);
        return ResponseEntity.ok(shift);
    }
}
