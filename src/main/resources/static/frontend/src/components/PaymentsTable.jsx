// src/components/PaymentTable.jsx
export default function PaymentTable({ payments }) {
  if (!payments || payments.length === 0) {
    return <p>No payments found.</p>;
  }

  return (
    <table className="w-full table-auto border-collapse">
      <thead>
        <tr className="bg-gray-200">
          <th className="border px-2 py-1">Sale No</th>
          <th className="border px-2 py-1">Customer</th>
          <th className="border px-2 py-1">Amount (Tsh)</th>
          <th className="border px-2 py-1">Payment Type</th>
          <th className="border px-2 py-1">Date</th>
        </tr>
      </thead>
      <tbody>
        {payments.map((p) => (
          <tr key={p.saleId} className="hover:bg-gray-100">
            <td className="border px-2 py-1">{p.saleNo}</td>
            <td className="border px-2 py-1">{p.customerName || "Walk-in"}</td>
            <td className="border px-2 py-1">{p.total.toLocaleString()} Tsh</td>
            <td className="border px-2 py-1">{p.paymentType}</td>
            <td className="border px-2 py-1">{new Date(p.createdAt).toLocaleString()}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
            }
