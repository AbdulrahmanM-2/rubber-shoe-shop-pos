CREATE TABLE product_variants (
  id SERIAL PRIMARY KEY,
  product_id INT NOT NULL REFERENCES products(id) ON DELETE CASCADE,
  size VARCHAR(10) NOT NULL,     -- 36, 37, 38, 40, etc
  color VARCHAR(30) NOT NULL,
  cost_price NUMERIC(10,2) NOT NULL,
  selling_price NUMERIC(10,2) NOT NULL,
  quantity INT DEFAULT 0,
  reorder_level INT DEFAULT 5,
  UNIQUE (product_id, size, color)
);
