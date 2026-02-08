-- Orders table
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    order_no VARCHAR(30) UNIQUE NOT NULL,        -- e.g., OR-20260204-001
    user_id INT NOT NULL REFERENCES users(id),   -- cashier or user who created order
    customer_id INT REFERENCES customers(id),    -- optional, for credit sales
    total NUMERIC(10,2) NOT NULL DEFAULT 0,     -- total order amount
    payment_type VARCHAR(20) NOT NULL,          -- CASH, MOBILE, CREDIT
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING', -- PENDING, COMPLETED, CANCELLED
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- Order items table
CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    variant_id INT NOT NULL REFERENCES product_variants(id),
    quantity INT NOT NULL DEFAULT 1,
    unit_price NUMERIC(10,2) NOT NULL,
    total NUMERIC(10,2) NOT NULL
);

-- Trigger to update 'updated_at' on order modification
CREATE OR REPLACE FUNCTION update_order_timestamp()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = NOW();
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_order
BEFORE UPDATE ON orders
FOR EACH ROW
EXECUTE FUNCTION update_order_timestamp();
