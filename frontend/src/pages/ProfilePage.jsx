import { useState, useEffect } from "react";
import Header from "../components/Header";

export default function Profile() {
  const [user, setUser] = useState({
    fullName: "",
    username: "",
    email: "",
    role: "",
  });

  const [editing, setEditing] = useState(false);
  const [formData, setFormData] = useState({ ...user });

  useEffect(() => {
    // Fetch current user profile
    fetch("/api/users/me") // Replace with your backend endpoint
      .then(res => res.json())
      .then(data => {
        setUser(data);
        setFormData(data);
      })
      .catch(err => console.error(err));
  }, []);

  const handleChange = (e) => {
    setFormData(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSave = () => {
    // Send update to backend
    fetch("/api/users/me", {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formData)
    })
    .then(res => res.json())
    .then(data => {
      setUser(data);
      setEditing(false);
      alert("Profile updated successfully!");
    })
    .catch(err => console.error(err));
  };

  return (
    <div className="min-h-screen bg-gray-100">
      <Header />
      <main className="p-6 max-w-3xl mx-auto">
        <h1 className="text-2xl font-bold mb-6">User Profile</h1>

        <div className="bg-white p-6 rounded shadow-md flex flex-col gap-4">
          <div className="flex flex-col sm:flex-row sm:justify-between sm:items-center">
            <label className="font-semibold">Full Name:</label>
            {editing ? (
              <input
                type="text"
                name="fullName"
                value={formData.fullName}
                onChange={handleChange}
                className="border rounded px-3 py-2 w-full sm:w-2/3"
              />
            ) : (
              <span>{user.fullName}</span>
            )}
          </div>

          <div className="flex flex-col sm:flex-row sm:justify-between sm:items-center">
            <label className="font-semibold">Username:</label>
            <span>{user.username}</span>
          </div>

          <div className="flex flex-col sm:flex-row sm:justify-between sm:items-center">
            <label className="font-semibold">Email:</label>
            {editing ? (
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                className="border rounded px-3 py-2 w-full sm:w-2/3"
              />
            ) : (
              <span>{user.email}</span>
            )}
          </div>

          <div className="flex flex-col sm:flex-row sm:justify-between sm:items-center">
            <label className="font-semibold">Role:</label>
            <span>{user.role}</span>
          </div>

          <div className="flex justify-end gap-4 mt-4">
            {editing ? (
              <>
                <button
                  onClick={() => setEditing(false)}
                  className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400"
                >
                  Cancel
                </button>
                <button
                  onClick={handleSave}
                  className="px-4 py-2 bg-primary text-white rounded hover:bg-blue-700"
                >
                  Save
                </button>
              </>
            ) : (
              <button
                onClick={() => setEditing(true)}
                className="px-4 py-2 bg-primary text-white rounded hover:bg-blue-700"
              >
                Edit Profile
              </button>
            )}
          </div>
        </div>
      </main>
    </div>
  );
      }
