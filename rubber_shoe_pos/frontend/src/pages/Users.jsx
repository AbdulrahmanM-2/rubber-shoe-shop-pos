import { useEffect, useState } from "react";

const API_URL = "http://localhost:8080/api/users";

export default function Users() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);

  const [showModal, setShowModal] = useState(false);
  const [form, setForm] = useState({
    username: "",
    role: "CASHIER",
    password: "",
  });

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    setLoading(true);
    const res = await fetch(API_URL);
    const data = await res.json();
    setUsers(data);
    setLoading(false);
  };

  const handleCreateUser = async (e) => {
    e.preventDefault();

    await fetch(API_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    });

    setShowModal(false);
    setForm({ username: "", role: "CASHIER", password: "" });
    fetchUsers();
  };

  const toggleStatus = async (id) => {
    await fetch(`${API_URL}/${id}/toggle`, { method: "PUT" });
    fetchUsers();
  };

  const resetPassword = async (id) => {
    if (!window.confirm("Reset password for this user?")) return;
    await fetch(`${API_URL}/${id}/reset-password`, { method: "PUT" });
    alert("Password reset to default");
  };

  return (
    <div className="p-6">
      {/* Header */}
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">User Management</h1>
        <button
          onClick={() => setShowModal(true)}
          className="bg-blue-600 text-white px-4 py-2 rounded"
        >
          + New User
        </button>
      </div>

      {/* Table */}
      <div className="bg-white shadow rounded overflow-x-auto">
        {loading ? (
          <p className="p-4">Loading users...</p>
        ) : (
          <table className="w-full text-sm">
            <thead className="bg-gray-100">
              <tr>
                <th className="p-3 text-left">Username</th>
                <th className="p-3 text-left">Role</th>
                <th className="p-3 text-left">Status</th>
                <th className="p-3 text-left">Actions</th>
              </tr>
            </thead>
            <tbody>
              {users.map((u) => (
                <tr key={u.id} className="border-t">
                  <td className="p-3">{u.username}</td>
                  <td className="p-3">
                    <span
                      className={`px-2 py-1 rounded text-xs ${
                        u.role === "MANAGER"
                          ? "bg-purple-100 text-purple-700"
                          : "bg-blue-100 text-blue-700"
                      }`}
                    >
                      {u.role}
                    </span>
                  </td>
                  <td className="p-3">
                    <span
                      className={`px-2 py-1 rounded text-xs ${
                        u.enabled
                          ? "bg-green-100 text-green-700"
                          : "bg-red-100 text-red-700"
                      }`}
                    >
                      {u.enabled ? "Active" : "Disabled"}
                    </span>
                  </td>
                  <td className="p-3 space-x-2">
                    <button
                      onClick={() => toggleStatus(u.id)}
                      className="px-3 py-1 bg-gray-600 text-white rounded text-xs"
                    >
                      {u.enabled ? "Disable" : "Enable"}
                    </button>

                    <button
                      onClick={() => resetPassword(u.id)}
                      className="px-3 py-1 bg-orange-600 text-white rounded text-xs"
                    >
                      Reset Password
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>

      {/* Create User Modal */}
      {showModal && (
        <div className="fixed inset-0 bg-black/40 flex items-center justify-center">
          <div className="bg-white p-6 rounded w-full max-w-md">
            <h2 className="text-lg font-semibold mb-4">Create User</h2>

            <form onSubmit={handleCreateUser} className="space-y-4">
              <input
                type="text"
                placeholder="Username"
                required
                className="w-full border p-2 rounded"
                value={form.username}
                onChange={(e) =>
                  setForm({ ...form, username: e.target.value })
                }
              />

              <select
                className="w-full border p-2 rounded"
                value={form.role}
                onChange={(e) =>
                  setForm({ ...form, role: e.target.value })
                }
              >
                <option value="CASHIER">Cashier</option>
                <option value="MANAGER">Manager</option>
              </select>

              <input
                type="password"
                placeholder="Temporary Password"
                required
                className="w-full border p-2 rounded"
                value={form.password}
                onChange={(e) =>
                  setForm({ ...form, password: e.target.value })
                }
              />

              <div className="flex justify-end space-x-2">
                <button
                  type="button"
                  onClick={() => setShowModal(false)}
                  className="px-4 py-2 bg-gray-300 rounded"
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="px-4 py-2 bg-blue-600 text-white rounded"
                >
                  Create
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
    }
