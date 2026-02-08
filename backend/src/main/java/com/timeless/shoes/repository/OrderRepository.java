package com.timeless.shoes.repository;

import com.timeless.shoes.entity.CashierShift;
import com.timeless.shoes.entity.Order;
import com.timeless.shoes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Find all orders for a specific cashier
     */
    List<Order> findByCashier(User cashier);

    /**
     * Find all orders for a specific shift
     */
    List<Order> findByShift(CashierShift shift);

    /**
     * Find all orders for a specific cashier's active shift
     */
    default List<Order> findByCashierAndActiveShift(User cashier, CashierShift activeShift) {
        return findByShift(activeShift);
    }

    /**
     * Find all orders (manager access)
     */
    List<Order> findAll();
}
