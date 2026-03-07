export default function CustomerTable({ customers }) {
  if (!customers || customers.length === 0) return <p>No customers found.</p>;

  return (
    <table className="w-full table-auto border-collapse">
      <thead>
        <tr className="bg-gray-200">
          <th className="border px-2 py-1">Customer ID</th>
          <th className="border px-2 py-1">Name</th>
          <th className="border px-2 py-1">Email</th>
          <th className="border px-2 py-1">Phone</th>
        </tr>
      </thead>
      <tbody>
        {customers.map(c => (
          <tr key={c.id} className="hover:bg-gray-100">
            <td className="border px-2 py-1">{c.id}</td>
            <td className="border px-2 py-1">{c.name}</td>
            <td className="border px-2 py-1">{c.email}</td>
            <td className="border px-2 py-1">{c.phone}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
