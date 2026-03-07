package com.timeless.shoes.sales;
import com.timeless.shoes.product.ProductVariant;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "sale_items")
public class SaleItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id", nullable = false)
    private ProductVariant variant;
    private int quantity;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    private BigDecimal total;
    public SaleItem() {}
    public SaleItem(Sale sale, ProductVariant variant, int quantity, BigDecimal unitPrice, BigDecimal total) {
        this.sale = sale;
        this.variant = variant;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Sale getSale() { return sale; }
    public void setSale(Sale s) { this.sale = s; }
    public ProductVariant getVariant() { return variant; }
    public void setVariant(ProductVariant v) { this.variant = v; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int q) { this.quantity = q; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal u) { this.unitPrice = u; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal t) { this.total = t; }
}
