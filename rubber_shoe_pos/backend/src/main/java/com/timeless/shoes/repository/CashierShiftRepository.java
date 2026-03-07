package com.timeless.shoes.repository;

import com.timeless.shoes.entity.CashierShift;
import com.timeless.shoes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CashierShiftRepository extends JpaRepository<CashierShift, Long> {

    /**
     * Find the active shift for a specific cashier
     */
    Optional<CashierShift> findByCashierAndClosedAtIsNull(User cashier);

    /**
     * Find all shifts for a cashier
     */
    List<CashierShift> findByCashier(User cashier);

    /**
     * Find all shifts between two timestamps
     */
    List<CashierShift> findByOpenedAtBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Find all active shifts
     */
    List<CashierShift> findByClosedAtIsNull();
}
