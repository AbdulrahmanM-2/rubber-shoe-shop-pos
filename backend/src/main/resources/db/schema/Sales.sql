CREATE TABLE sales (
  id SERIAL PRIMARY KEY,
  sale_no VARCHAR(30) UNIQUE NOT NULL,
  user_id INT NOT NULL REFERENCES users(id),
  customer_id INT REFERENCES customers(id),
  total NUMERIC(10,2) NOT NULL,
  payment_type VARCHAR(20) NOT NULL, -- CASH, MOBILE, CREDIT
  created_at TIMESTAMP DEFAULT NOW()
);
