CREATE TABLE stock_movements (
  id SERIAL PRIMARY KEY,
  variant_id INT NOT NULL REFERENCES product_variants(id),
  type VARCHAR(10) NOT NULL, -- IN, OUT, ADJUST
  quantity INT NOT NULL,
  reference VARCHAR(50),     -- SALE_NO, RESTOCK_NO, MANUAL_ADJUST
  created_at TIMESTAMP DEFAULT NOW()
);
