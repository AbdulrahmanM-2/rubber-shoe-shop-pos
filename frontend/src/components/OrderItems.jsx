const OrderItems = ({ order, products, messages }) => (
  <div className="overflow-x-auto">
  <h3 className="text-lg font-semibold mb-2">{messages.orderItems}</h3>
  <table className="w-full text-left border-collapse">
    <thead className="bg-gray-100">
      <tr>
        <th className="border px-2 py-1">{messages.product}</th>
        <th className="border px-2 py-1">{messages.size}</th>
        <th className="border px-2 py-1">{messages.color}</th>
        <th className="border px-2 py-1">{messages.quantity}</th>
        <th className="border px-2 py-1">{messages.unitPrice}</th>
        <th className="border px-2 py-1">{messages.lineTotal}</th>
      </tr>
    </thead>
    <tbody>
      {order.items.map((item) => (
        <tr key={item.productId} className="hover:bg-gray-50">
          <td className="border px-2 py-1">{products.find((p) => p.id === item.productId)?.name}</td>
          <td className="border px-2 py-1">{item.size}</td>
          <td className="border px-2 py-1">{item.color}</td>
          <td className="border px-2 py-1">{item.quantity}</td>
          <td className="border px-2 py-1">{item.unitPrice}</td>
          <td className="border px-2 py-1">{item.lineTotal}</td>
        </tr>
      ))}
    </tbody>
  </table>
</div>
);

export default OrderItems;
