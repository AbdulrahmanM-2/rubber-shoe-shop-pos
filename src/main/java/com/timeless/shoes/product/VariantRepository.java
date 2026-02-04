package com.timeless.shoes.report;

import com.timeless.shoes.product.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<ProductVariant, Long> {

    /**
     * Returns all product variants where quantity is at or below reorder level
     * Ordered by quantity ascending (lowest stock first)
     */
    @Query("""
        SELECT v
        FROM ProductVariant v
        WHERE v.quantity <= v.reorderLevel
        ORDER BY v.quantity ASC
    """)
    List<ProductVariant> lowStock();
}
