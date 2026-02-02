import { useEffect, useState } from "react";
import { getVariants, createSale, api } from "../services/api";
import ProductSearch from "../components/ProductSearch";
import Cart from "../components/Cart";
import PaymentButtons from "../components/PaymentButtons";
import Receipt from "../components/Receipt";
import LowStockAlert from "../components/LowStockAlert";

export default function POS() {
  const [variants, setVariants] = useState([]);
  const [cart, setCart] = useState([]);
  const [lastSale, setLastSale] = useState(null);
  const [dashboard, setDashboard] = useState({ total: 0, transactions: 0 });

  useEffect(() => {
    fetchVariants();
    fetchDashboard();
  }, []);

  const fetchVariants = async () => {
    const { data } = await getVariants();
    setVariants(data);
  };

  const fetchDashboard = async () => {
    const { data } = await api.get("/reports/today");
    setDashboard(data);
  };

  const addToCart = (variant) => {
    const existing = cart.find((c) => c.id === variant.id);
    if (existing) {
      setCart(
        cart.map((c) =>
          c.id === variant.id ? { ...c, quantity: c.quantity + 1 } : c
        )
      );
    } else {
      setCart([...cart, { ...variant, quantity: 1 }]);
    }
  };

  const handlePayment = async (type) => {
    const saleRequest = {
      items: cart.map((i) => ({ variantId: i.id, quantity: i.quantity })),
      paymentType: type,
      customerId: null,
    };
    try {
      const { data } = await createSale(saleRequest);
      alert("Sale completed!");
      setLastSale(data);
      setCart([]);
      fetchVariants();
      fetchDashboard();
    } catch (err) {
      alert("Sale failed: " + (err.response?.data?.message || err.message));
    }
  };

  return (
    <div style={{ padding: 10 }}>
      <h2>Rubber Shoes Shop POS</h2>

      <LowStockAlert />

      <div style={{ display: "flex", gap: 20 }}>
        <ProductSearch variants={variants} addToCart={addToCart} />
        <Cart cart={cart} />
        <PaymentButtons onPay={handlePayment} />
      </div>

      <div style={{ marginTop: 20 }}>
        <h3>Today's Summary</h3>
        <p>Total Sales: {dashboard.total} Tsh</p>
        <p>Transactions: {dashboard.totalTransactions}</p>
      </div>

      <Receipt sale={lastSale} />
    </div>
  );
}
