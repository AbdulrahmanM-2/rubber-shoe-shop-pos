package com.shop.sales;

import java.util.List;

public class SaleRequest {

  public List<SaleItemRequest> items;
  public String paymentType; // CASH, MOBILE, CREDIT
  public Long customerId;
}
