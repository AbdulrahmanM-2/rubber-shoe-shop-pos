import { useState, useEffect } from "react";
import Header from "../components/Header";

export default function Customers() {
  const [customers, setCustomers] = useState([]);
  const [formVisible, setFormVisible] = useState(false);
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    phone: "",
    address: "",
  });
  const [editingId, setEditingId] = useState(null);

  // Fetch customers from backend
  useEffect(() => {
    fetch("/api/customers")
      .then(res => res.json())
      .then(data => setCustomers(data))
      .catch(err => console.error(err));
  }, []);

  const handleChange = (e) => {
    setFormData(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = () => {
    const method = editingId ? "PUT" : "POST";
    const url = editingId ? `/api/customers/${editingId}` : "/api/customers";

    fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formData),
    })
      .then(res => res.json())
      .then(data => {
        if (editingId) {
          setCustomers(prev => prev.map(c => c.id === editingId ? data : c));
        } else {
          setCustomers(prev => [...prev, data]);
        }
        setFormData({ name: "", email: "", phone: "", address: "" });
        setFormVisible(false);
        setEditingId(null);
      })
      .catch(err => console.error(err));
  };

  const handleEdit = (customer) => {
    setEditingId(customer.id);
    setFormData(customer);
    setFormVisible(true);
  };

  const handleDelete = (id) => {
    if (!confirm("Are you sure you want to delete this customer?")) return;

    fetch(`/api/customers/${id}`, { method: "DELETE" })
      .then(() => setCustomers(prev => prev.filter(c => c.id !== id)))
      .catch(err => console.error(err));
  };

  return (
    <div className="min-h-screen bg-gray-100">
      <Header />

      <main className="p-6 max-w-5xl mx-auto">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-2xl font-bold">Customers</h1>
          <button
            onClick={() => setFormVisible(!formVisible)}
            className="px-4 py-2 bg-primary text-white rounded hover:bg-blue-700"
          >
            {formVisible ? "Cancel" : "Add Customer"}
          </button>
        </div>

        {formVisible && (
          <div className="bg-white p-6 rounded shadow-md mb-6">
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleChange}
                placeholder="Full Name"
                className="border rounded px-3 py-2"
              />
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                placeholder="Email"
                className="border rounded px-3 py-2"
              />
              <input
                type="text"
                name="phone"
                value={formData.phone}
                onChange={handleChange}
                placeholder="Phone"
                className="border rounded px-3 py-2"
              />
              <input
                type="text"
                name="address"
                value={formData.address}
                onChange={handleChange}
                placeholder="Address"
                className="border rounded px-3 py-2"
              />
            </div>
            <div className="flex justify-end mt-4">
              <button
                onClick={handleSubmit}
                className="px-4 py-2 bg-success text-white rounded hover:bg-green-700"
              >
                {editingId ? "Update Customer" : "Add Customer"}
              </button>
            </div>
          </div>
        )}

        <div className="bg-white p-6 rounded shadow-md">
          <table className="min-w-full table-auto">
            <thead>
              <tr className="bg-gray-200 text-left">
                <th className="px-4 py-2">Name</th>
                <th className="px-4 py-2">Email</th>
                <th className="px-4 py-2">Phone</th>
                <th className="px-4 py-2">Address</th>
                <th className="px-4 py-2">Actions</th>
              </tr>
            </thead>
            <tbody>
              {customers.map(c => (
                <tr key={c.id} className="border-b">
                  <td className="px-4 py-2">{c.name}</td>
                  <td className="px-4 py-2">{c.email}</td>
                  <td className="px-4 py-2">{c.phone}</td>
                  <td className="px-4 py-2">{c.address}</td>
                  <td className="px-4 py-2 flex gap-2">
                    <button
                      onClick={() => handleEdit(c)}
                      className="px-2 py-1 bg-primary text-white rounded hover:bg-blue-700 text-sm"
                    >
                      Edit
                    </button>
                    <button
                      onClick={() => handleDelete(c.id)}
                      className="px-2 py-1 bg-danger text-white rounded hover:bg-red-700 text-sm"
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
              {customers.length === 0 && (
                <tr>
                  <td colSpan="5" className="text-center py-4 text-gray-500">
                    No customers found.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </main>
    </div>
  );
                }
