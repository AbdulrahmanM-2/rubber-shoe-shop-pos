const OrderTotals = ({ order, messages }) => (
  <div className="order-totals">
    <h3>{messages.subtotal} / {messages.totalPaid} / {messages.balance}</h3>
    <p>{messages.subtotal}: {order.subtotal}</p>
    <p>{messages.totalPaid}: {order.totalPaid}</p>
    <p>{messages.balance}: {order.balance}</p>
    <p>{messages.orderStatus}: {order.status}</p>
  </div>
);

export default OrderTotals;
