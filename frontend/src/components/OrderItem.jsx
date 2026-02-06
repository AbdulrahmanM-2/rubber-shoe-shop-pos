import React from "react";

const OrderItems = ({ order, products }) => {
  return (
    <div className="order-items">
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
    </div>
  );
};

export default OrderItems;
