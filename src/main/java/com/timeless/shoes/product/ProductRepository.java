package com.timeless.shoes.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    @Query("""
        SELECT v
        FROM ProductVariant v
        WHERE v.quantity <= v.reorderLevel
        ORDER BY v.quantity ASC
    """)
    List<ProductVariant> findLowStockVariants();

    boolean existsByProductIdAndSizeAndColor(Long productId, String size, String color);
}
