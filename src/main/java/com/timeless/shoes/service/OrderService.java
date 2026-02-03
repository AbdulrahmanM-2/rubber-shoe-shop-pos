package com.timeless.shoes.service;

import com.timeless.shoes.model.Product;
import com.timeless.shoes.repository.ProductRepository;
import com.timeless.shoes.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product getByBarcode(String barcode) {
        return productRepository.findByBarcodeAndActiveTrue(barcode)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public List<Product> getAllActive() {
        return productRepository.findByActiveTrue();
    }

    public void reduceStock(Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            throw new IllegalStateException("Insufficient stock");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }
}
