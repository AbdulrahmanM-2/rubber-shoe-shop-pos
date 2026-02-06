import React from "react";

const OrderTotals = ({ order }) => {
  return (
    <div className="order-totals">
      <h3>Totals</h3>
      <p>Subtotal: {order.subtotal}</p>
      <p>Total Paid: {order.totalPaid}</p>
      <p>Balance: {order.balance}</p>
      <p>Status: {order.status}</p>
    </div>
  );
};

export default OrderTotals;
