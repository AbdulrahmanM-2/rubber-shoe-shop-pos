const OrderTotals = ({ order, messages }) => (
  <div className="space-y-2">
  <h3 className="text-lg font-semibold">{messages.subtotal} / {messages.totalPaid} / {messages.balance}</h3>
  <p>{messages.subtotal}: <span className="font-bold">{order.subtotal}</span></p>
  <p>{messages.totalPaid}: <span className="font-bold">{order.totalPaid}</span></p>
  <p>{messages.balance}: <span className="font-bold">{order.balance}</span></p>
  <p>{messages.orderStatus}: <span className="font-bold">{order.status}</span></p>
</div>
);

export default OrderTotals;
