package com.shop.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<ProductVariant, Long> {
}
