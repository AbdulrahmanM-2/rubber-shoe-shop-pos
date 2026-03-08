package com.timeless.shoes.sales;
import com.timeless.shoes.product.ProductVariant;
import com.timeless.shoes.product.ProductVariantRepository;
import com.timeless.shoes.sales.dto.CreateSaleRequest;
import com.timeless.shoes.sales.dto.SaleDto;
import com.timeless.shoes.sales.dto.SaleItemDto;
import com.timeless.shoes.customers.Customer;
import com.timeless.shoes.repository.CustomerRepository;
import com.timeless.shoes.dashboard.DashboardEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final ProductVariantRepository variantRepository;
    private final CustomerRepository customerRepository;
    private final DashboardEventPublisher dashboardPublisher;

    public SaleService(SaleRepository saleRepository, ProductVariantRepository variantRepository,
                       CustomerRepository customerRepository, DashboardEventPublisher dashboardPublisher) {
        this.saleRepository = saleRepository;
        this.variantRepository = variantRepository;
        this.customerRepository = customerRepository;
        this.dashboardPublisher = dashboardPublisher;
    }

    @Transactional
    public SaleDto createSale(CreateSaleRequest request) {
        Sale sale = new Sale();
        sale.setPaymentType(request.getPaymentType());
        sale.setCreatedAt(LocalDateTime.now());

        if (request.getCustomerId() != null) {
            Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
            sale.setCustomer(customer);
        }

        sale.setSaleNo(generateSaleNo());

        List<SaleItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (SaleItemDto itemDto : request.getItems()) {
            ProductVariant variant = variantRepository.findById(itemDto.getVariantId())
                .orElseThrow(() -> new RuntimeException("Variant not found"));

            if (variant.getQuantity() < itemDto.getQuantity()) {
                throw new RuntimeException("Not enough stock for " + variant.getProduct().getName());
            }

            variant.setQuantity(variant.getQuantity() - itemDto.getQuantity());
            variantRepository.save(variant);

            BigDecimal itemTotal = variant.getSellingPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity()));
            total = total.add(itemTotal);

            items.add(new SaleItem(sale, variant, itemDto.getQuantity(), variant.getSellingPrice(), itemTotal));
        }

        sale.setTotal(total);
        sale.setItems(items);
        Sale saved = saleRepository.save(sale);
        dashboardPublisher.broadcastUpdate();
        return mapToDto(saved);
    }

    private String generateSaleNo() {
        String datePart = LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        long count = saleRepository.count() + 1;
        return "S-" + datePart + "-" + String.format("%03d", count);
    }

    private SaleDto mapToDto(Sale sale) {
        List<SaleItemDto> itemDtos = new ArrayList<>();
        for (SaleItem item : sale.getItems()) {
            SaleItemDto dto = new SaleItemDto();
            dto.setVariantId(item.getVariant().getId());
            dto.setProductName(item.getVariant().getProduct().getName());
            dto.setSize(item.getVariant().getSize());
            dto.setColor(item.getVariant().getColor());
            dto.setUnitPrice(item.getUnitPrice());
            dto.setQuantity(item.getQuantity());
            dto.setTotal(item.getTotal());
            itemDtos.add(dto);
        }
        SaleDto dto = new SaleDto();
        dto.setSaleId(sale.getId());
        dto.setSaleNo(sale.getSaleNo());
        dto.setCustomerName(sale.getCustomer() != null ? sale.getCustomer().getName() : null);
        dto.setPaymentType(sale.getPaymentType());
        dto.setTotal(sale.getTotal());
        dto.setItems(itemDtos);
        dto.setCreatedAt(sale.getCreatedAt());
        return dto;
    }

    public List<SaleDto> getAllSales(String date) {
        List<Sale> sales;
        if (date != null) {
            LocalDateTime start = LocalDateTime.parse(date + "T00:00:00");
            LocalDateTime end = LocalDateTime.parse(date + "T23:59:59");
            sales = saleRepository.findByCreatedAtBetween(start, end);
        } else {
            sales = saleRepository.findAll();
        }
        List<SaleDto> dtos = new ArrayList<>();
        for (Sale sale : sales) dtos.add(mapToDto(sale));
        return dtos;
    }

    public SaleDto getSaleById(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
            .orElseThrow(() -> new RuntimeException("Sale not found"));
        return mapToDto(sale);
    }
}
