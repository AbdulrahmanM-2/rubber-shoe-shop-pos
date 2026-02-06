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
    <div className="new-order p-4 bg-gray-50 min-h-screen">
  {/* Language Switcher */}
  <div className="flex justify-end mb-4">
    <label className="flex items-center space-x-2">
      <span className="font-medium">{messages.languageLabel || "Language:"}</span>
      <select
        className="border rounded px-2 py-1"
        value={lang}
        onChange={(e) => setLang(e.target.value)}
      >
        <option value="en">English</option>
        <option value="sw">Kiswahili</option>
      </select>
    </label>
  </div>

  {/* Order Header */}
  <div className="mb-6 bg-white shadow rounded p-4 flex justify-between items-center">
    <div>
      <h2 className="text-2xl font-bold">{messages.orderNumber}: {order.orderNumber}</h2>
      <p className="text-gray-600">{messages.orderStatus}: {order.status}</p>
    </div>
  </div>

  <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
    {/* Left Column: Product Selector */}
    <div className="md:col-span-1 bg-white p-4 rounded shadow">
      <ProductSelector
        products={products}
        selectedProduct={selectedProduct}
        setSelectedProduct={setSelectedProduct}
        quantity={quantity}
        setQuantity={setQuantity}
        addItem={addItem}
        messages={messages}
      />
    </div>

    {/* Middle Column: Order Items */}
    <div className="md:col-span-2 bg-white p-4 rounded shadow overflow-auto max-h-[400px]">
      <OrderItems order={order} products={products} messages={messages} />
    </div>

    {/* Right Column: Totals + Payment */}
    <div className="md:col-span-1 space-y-4">
      <div className="bg-white p-4 rounded shadow">
        <OrderTotals order={order} messages={messages} />
      </div>
      <div className="bg-white p-4 rounded shadow">
        <PaymentPanel
          paymentAmount={paymentAmount}
          setPaymentAmount={setPaymentAmount}
          paymentMethod={paymentMethod}
          setPaymentMethod={setPaymentMethod}
          addPayment={addPayment}
          messages={messages}
        />
      </div>
    </div>
  </div>
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
<div className="new-order p-4 bg-gray-50 min-h-screen relative pb-24">
  {/* Language Switcher */}
  <div className="flex justify-end mb-4">
    <label className="flex items-center space-x-2">
      <span className="font-medium">{messages.languageLabel || "Language:"}</span>
      <select
        className="border rounded px-2 py-1"
        value={lang}
        onChange={(e) => setLang(e.target.value)}
      >
        <option value="en">English</option>
        <option value="sw">Kiswahili</option>
      </select>
    </label>
  </div>

  {/* Order Header */}
  <div className="mb-6 bg-white shadow rounded p-4 flex justify-between items-center">
    <div>
      <h2 className="text-2xl font-bold">{messages.orderNumber}: {order.orderNumber}</h2>
      <p className="text-gray-600">{messages.orderStatus}: {order.status}</p>
    </div>
  </div>

  {/* Main Grid */}
  <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
    <div className="md:col-span-1 bg-white p-4 rounded shadow">
      <ProductSelector
        products={products}
        selectedProduct={selectedProduct}
        setSelectedProduct={setSelectedProduct}
        quantity={quantity}
        setQuantity={setQuantity}
        addItem={addItem}
        messages={messages}
      />
    </div>

    <div className="md:col-span-2 bg-white p-4 rounded shadow overflow-auto max-h-[400px]">
      <OrderItems order={order} products={products} messages={messages} />
    </div>

    <div className="md:col-span-1 space-y-4">
      <div className="bg-white p-4 rounded shadow">
        <OrderTotals order={order} messages={messages} />
      </div>
      <div className="bg-white p-4 rounded shadow">
        <PaymentPanel
          paymentAmount={paymentAmount}
          setPaymentAmount={setPaymentAmount}
          paymentMethod={paymentMethod}
          setPaymentMethod={setPaymentMethod}
          addPayment={addPayment}
          messages={messages}
        />
      </div>
    </div>
  </div>

  {/* Fixed Footer */}
  <div className="fixed bottom-0 left-0 w-full bg-gray-800 text-white flex justify-between items-center px-6 py-4 shadow-lg">
    <div>
      <p className="text-lg font-semibold">
        {messages.balance}: {order.balance}
      </p>
    </div>
    <button
      className="bg-green-600 px-6 py-2 rounded hover:bg-green-700 text-white font-bold"
      onClick={() => alert(messages.completeOrder)}
    >
      {messages.completeOrder}
    </button>
  </div>
</div>
  );
};

export default NewOrder;
