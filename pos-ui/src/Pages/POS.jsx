import { useEffect, useState } from "react";
import { getVariants, createSale } from "../services/api";
import ProductSearch from "../components/ProductSearch";
import Cart from "../components/Cart";
import PaymentButtons from "../components/PaymentButtons";

export default function POS() {
  const [variants, setVariants] = useState([]);
  const [cart, setCart] = useState([]);

  useEffect(() => {
    fetchVariants();
  }, []);

  const fetchVariants = async () => {
    const { data } = await getVariants();
    setVariants(data);
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
      await createSale(saleRequest);
      alert("Sale completed!");
      setCart([]);
      fetchVariants();
    } catch (err) {
      alert("Sale failed: " + err.response.data.message || err.message);
    }
  };

  return (
    <div style={{ display: "flex" }}>
      <ProductSearch variants={variants} addToCart={addToCart} />
      <Cart cart={cart} />
      <PaymentButtons onPay={handlePayment} />
    </div>
  );
}
