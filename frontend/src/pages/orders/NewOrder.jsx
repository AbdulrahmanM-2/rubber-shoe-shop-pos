import React, { useState, useEffect } from "react";
import axios from "axios";

const API_BASE = "/api"; // Spring Boot API base

const NewOrder = () => {
  const [order, setOrder] = useState(null);
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [quantity, setQuantity] = useState(1);
  const [paymentAmount, setPaymentAmount] = useState(0);
  const [paymentMethod, setPaymentMethod] = useState("CASH");
  const [customerId, setCustomerId] = useState(null);

  // Create a new order on load
  useEffect(() => {
    axios
      .post(`${API_BASE}/orders`, { customerId: customerId, userId: 1 }) // userId hardcoded for demo
      .then((res) => setOrder(res.data))
      .catch((err) => console.error(err));

    // Load products
    axios
      .get(`${API_BASE}/products`)
      .then((res) => setProducts(res.data))
      .catch((err) => console.error(err));
  }, []);

  // Add item to order
  const addItem = () => {
    if (!selectedProduct) return alert("Select a product first");
    axios
      .post(`${API_BASE}/orders/${order.id}/items`, {
        productId: selectedProduct.id,
        size: selectedProduct.size,
        color: selectedProduct.color,
        quantity: quantity,
      })
      .then((res) => setOrder(res.data))
      .catch((err) => alert(err.response.data.message));
  };

  // Add payment
  const addPayment = () => {
    if (paymentAmount <= 0) return alert("Enter a valid payment amount");
    axios
      .post(`${API_BASE}/orders/${order.id}/payments`, {
        amount: paymentAmount,
        method: paymentMethod,
      })
      .then((res) => {
        setOrder(res.data);
        setPaymentAmount(0);
      })
      .catch((err) => alert(err.response.data.message));
  };

  if (!order) return <div>Loading order...</div>;

  return (
    <div className="new-order">
      <h2>Order Number: {order.orderNumber}</h2>
      <p>Status: {order.status}</p>

      <h3>Add Product</h3>
      <select
        value={selectedProduct?.id || ""}
        onChange={(e) =>
          setSelectedProduct(products.find((p) => p.id === parseInt(e.target.value)))
        }
      >
        <option value="">--Select Product--</option>
        {products.map((p) => (
          <option key={p.id} value={p.id}>
            {p.name} | {p.color} | Size: {p.size} | Stock: {p.stock} | Price: {p.unitPrice}
          </option>
        ))}
      </select>
      <input
        type="number"
        min="1"
        value={quantity}
        onChange={(e) => setQuantity(parseInt(e.target.value))}
      />
      <button onClick={addItem}>Add Item</button>

      <h3>Order Items</h3>
      <table>
        <thead>
          <tr>
            <th>Product</th>
            <th>Size</th>
            <th>Color</th>
            <th>Quantity</th>
            <th>Unit Price</th>
            <th>Line Total</th>
          </tr>
        </thead>
        <tbody>
          {order.items.map((item) => (
            <tr key={item.productId}>
              <td>{products.find((p) => p.id === item.productId)?.name}</td>
              <td>{item.size}</td>
              <td>{item.color}</td>
              <td>{item.quantity}</td>
              <td>{item.unitPrice}</td>
              <td>{item.lineTotal}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <h3>Totals</h3>
      <p>Subtotal: {order.subtotal}</p>
      <p>Total Paid: {order.totalPaid}</p>
      <p>Balance: {order.balance}</p>

      <h3>Add Payment</h3>
      <input
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
