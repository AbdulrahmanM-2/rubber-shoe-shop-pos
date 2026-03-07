// src/components/OrderTable.jsx
export default function OrderTable({ orders }) {
  if (!orders || orders.length === 0) return <p>No orders found.</p>;

  return (
    <table className="w-full table-auto border-collapse">
      <thead>
        <tr className="bg-gray-200">
          <th className="border px-2 py-1">Order No</th>
          <th className="border px-2 py-1">Customer</th>
          <th className="border px-2 py-1">Total (Tsh)</th>
          <th className="border px-2 py-1">Status</th>
          <th className="border px-2 py-1">Date</th>
        </tr>
      </thead>
      <tbody>
        {orders.map((o) => (
          <tr key={o.saleId} className="hover:bg-gray-100">
            <td className="border px-2 py-1">{o.saleNo}</td>
            <td className="border px-2 py-1">{o.customerName || "Walk-in"}</td>
            <td className="border px-2 py-1">{o.total.toLocaleString()} Tsh</td>
            <td className="border px-2 py-1">{o.status || "Pending"}</td>
            <td className="border px-2 py-1">{new Date(o.createdAt).toLocaleString()}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
