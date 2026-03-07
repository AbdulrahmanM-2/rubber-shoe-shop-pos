-- Payments table
CREATE TABLE payments (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    payment_date TIMESTAMP DEFAULT NOW(),
    payment_type VARCHAR(20) NOT NULL,     -- CASH, MOBILE, CREDIT
    amount NUMERIC(10,2) NOT NULL,         -- amount paid
    reference VARCHAR(50),                 -- optional: mobile txn id, receipt, etc.
    created_at TIMESTAMP DEFAULT NOW()
);

-- Optional: trigger to keep payments in sync with order total
CREATE OR REPLACE FUNCTION update_order_total()
RETURNS TRIGGER AS $$
BEGIN
    -- Update order total if needed (optional logic)
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Example trigger (not mandatory)
-- CREATE TRIGGER trg_update_order_total
-- AFTER INSERT OR UPDATE ON payments
-- FOR EACH ROW
-- EXECUTE FUNCTION update_order_total();
