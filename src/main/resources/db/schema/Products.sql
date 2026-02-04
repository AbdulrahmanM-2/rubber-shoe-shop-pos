CREATE TABLE products (
  id SERIAL PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  brand VARCHAR(100),
  gender VARCHAR(10),   -- MEN, WOMEN, KIDS, UNISEX
  category VARCHAR(50), -- Sandals, Slippers, Gumboots
  active BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT NOW()
);
