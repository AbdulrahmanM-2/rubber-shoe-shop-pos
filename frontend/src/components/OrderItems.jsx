const OrderItems = ({ order, products, messages }) => (
  <div className="order-items">
    <h3>{messages.orderItems}</h3>
    <table>
      <thead>
        <tr>
          <th>{messages.product}</th>
          <th>{messages.size}</th>
          <th>{messages.color}</th>
          <th>{messages.quantity}</th>
          <th>{messages.unitPrice}</th>
          <th>{messages.lineTotal}</th>
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

export default OrderItems;
