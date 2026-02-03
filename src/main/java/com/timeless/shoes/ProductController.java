package com.timeless.shoes.controller;

import com.timeless.shoes.model.Product;
import com.timeless.shoes.service.ProductService;
import com.timeless.shoes.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<Product> create(@RequestBody Product product) {
        return ApiResponse.success("Product created", productService.create(product));
    }

    @GetMapping
    public ApiResponse<List<Product>> getAll() {
        return ApiResponse.success("Products fetched", productService.getAllActive());
    }

    @GetMapping("/barcode/{barcode}")
    public ApiResponse<Product> getByBarcode(@PathVariable String barcode) {
        return ApiResponse.success("Product found", productService.getByBarcode(barcode));
    }
}
