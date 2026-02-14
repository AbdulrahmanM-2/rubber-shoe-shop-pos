package com.timeless.shoes.repository;

import com.timeless.shoes.product.Product;
import java.util.List;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findAll();
}
