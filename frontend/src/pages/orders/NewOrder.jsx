import React, { useState, useEffect } from "react";
import axios from "axios";

import en_messages from "./i18n/en_messages";
import sw_messages from "./i18n/sw_messages";

import ProductSelector from "./ProductSelector";
import OrderItems from "./OrderItems";
import OrderTotals from "./OrderTotals";
import PaymentPanel from "./PaymentPanel";

const API_BASE = "/api";

const NewOrder = () => {
  const [order, setOrder] = useState(null);
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [quantity, setQuantity] = useState(1);
  const [paymentAmount, setPaymentAmount] = useState(0);
  const [paymentMethod, setPaymentMethod] = useState("CASH");
  const [customerId, setCustomerId] = useState(null);

  // Language state
  const [lang, setLang] = useState("en");
  const messages = lang === "en" ? en_messages : sw_messages;

  // Load order & products
  useEffect(() => {
    axios
      .post(`${API_BASE}/orders`, { customerId, userId: 1 })
      .then((res) => setOrder(res.data))
      .catch(() => alert(messages.orderNotFound));

    axios
      .get(`${API_BASE}/products`)
      .then((res) => setProducts(res.data))
      .catch((err) => console.error(err));
  }, [messages]);

  const addItem = () => {
    if (!selectedProduct) return alert(messages.selectProductAlert);
    axios
      .post(`${API_BASE}/orders/${order.id}/items`, {
        productId: selectedProduct.id,
        size: selectedProduct.size,
        color: selectedProduct.color,
        quantity,
      })
      .then((res) => setOrder(res.data))
      .catch((err) => alert(err.response?.data?.message || messages.insufficientStock));
  };

  const addPayment = () => {
    if (paymentAmount <= 0) return alert(messages.paymentInvalid);
    axios
      .post(`${API_BASE}/orders/${order.id}/payments`, {
        amount: paymentAmount,
        method: paymentMethod,
      })
      .then((res) => {
        setOrder(res.data);
        setPaymentAmount(0);
        alert(messages.paymentSuccess);
      })
      .catch((err) => alert(err.response?.data?.message || messages.paymentError));
  };

  if (!order) return <div>{messages.loading}</div>;

  return (
    <div className="new-order">
      {/* Language Switcher */}
      <div className="language-switcher">
        <label>
          Language:
          <select value={lang} onChange={(e) => setLang(e.target.value)}>
            <option value="en">English</option>
            <option value="sw">Kiswahili</option>
          </select>
        </label>
      </div>

      <h2>{messages.orderNumber}: {order.orderNumber}</h2>
      <p>{messages.orderStatus}: {order.status}</p>

      <ProductSelector
        products={products}
        selectedProduct={selectedProduct}
        setSelectedProduct={setSelectedProduct}
        quantity={quantity}
        setQuantity={setQuantity}
        addItem={addItem}
        messages={messages} // pass messages to components
      />

      <OrderItems order={order} products={products} messages={messages} />
      <OrderTotals order={order} messages={messages} />

      <PaymentPanel
        paymentAmount={paymentAmount}
        setPaymentAmount={setPaymentAmount}
        paymentMethod={paymentMethod}
        setPaymentMethod={setPaymentMethod}
        addPayment={addPayment}
        messages={messages}
      />
    </div>
  );
};

export default NewOrder;      <input
        type="number"
        min="0"
        value={paymentAmount}
        onChange={(e) => setPaymentAmount(parseFloat(e.target.value))}
      />
      <select value={paymentMethod} onChange={(e) => setPaymentMethod(e.target.value)}>
        <option value="CASH">Cash</option>
        <option value="MOBILE_MONEY">Mobile Money</option>
        <option value="CREDIT">Credit</option>
      </select>
      <button onClick={addPayment}>Add Payment</button>
    </div>
  );
};

export default NewOrder;
