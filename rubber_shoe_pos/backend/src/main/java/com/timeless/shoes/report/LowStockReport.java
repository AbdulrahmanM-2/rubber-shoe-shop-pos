package com.timeless.shoes.report;

import java.math.BigDecimal;

public class LowStockReport {

    private final Long id;
    private final String productName;
    private final String size;
    private final String color;
    private final BigDecimal sellingPrice;
    private final int quantity;
    private final int reorderLevel;

    public LowStockReport(
            Long id,
            String productName,
            String size,
            String color,
            BigDecimal sellingPrice,
            int quantity,
            int reorderLevel
    ) {
        this.id = id;
        this.productName = productName;
        this.size = size;
        this.color = color;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }

    public Long getId() { return id; }
    public String getProductName() { return productName; }
    public String getSize() { return size; }
    public String getColor() { return color; }
    public BigDecimal getSellingPrice() { return sellingPrice; }
    public int getQuantity() { return quantity; }
    public int getReorderLevel() { return reorderLevel; }

    @Override
    public String toString() {
        return "LowStockReport{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", quantity=" + quantity +
                ", reorderLevel=" + reorderLevel +
                '}';
    }
    }
