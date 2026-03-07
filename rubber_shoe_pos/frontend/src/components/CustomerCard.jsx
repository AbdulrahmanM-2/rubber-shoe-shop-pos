// src/components/CustomerCard.jsx
export default function CustomerCard({ customer }) {
  return (
    <div className="bg-white rounded shadow-md p-4">
      <h3 className="font-bold">{customer.name}</h3>
      <p>Email: {customer.email || "N/A"}</p>
      <p>Phone: {customer.phone || "N/A"}</p>
    </div>
  );
}
