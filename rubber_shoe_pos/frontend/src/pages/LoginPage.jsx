import { useState } from "react";
import { useNavigate } from "react-router-dom";

// Reads from Railway env var — set REACT_APP_API_URL in Railway Variables tab
// Falls back to same origin (works when frontend is served by Spring Boot)
const API_URL = process.env.REACT_APP_API_URL
  ? `${process.env.REACT_APP_API_URL}/api/auth/login`
  : "/api/auth/login";

export default function LoginPage() {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);

    try {
      const res = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });

      if (!res.ok) {
        const msg = await res.text();
        throw new Error(msg || "Invalid username or password");
      }

      const data = await res.json();
      // data.data contains { username, role, token }
      const user = data.data || data;
      localStorage.setItem("user", JSON.stringify(user));

      if (user.role === "MANAGER") {
        navigate("/dashboard", { replace: true });
      } else {
        navigate("/pos", { replace: true });
      }
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-600 to-indigo-700">
      <div className="bg-white w-full max-w-md rounded-xl shadow-xl p-6">
        <h1 className="text-2xl font-bold text-center mb-1">Rubber Shoes POS</h1>
        <p className="text-sm text-gray-500 text-center mb-6">Staff Login</p>

        {error && (
          <div className="mb-4 bg-red-100 text-red-700 p-3 rounded text-sm">{error}</div>
        )}

        <form onSubmit={handleLogin} className="space-y-4">
          <div>
            <label className="block text-sm mb-1">Username</label>
            <input
              type="text"
              className="w-full border rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required autoFocus
            />
          </div>
          <div>
            <label className="block text-sm mb-1">Password</label>
            <input
              type="password"
              className="w-full border rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button
            type="submit"
            disabled={loading}
            className="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 rounded font-semibold disabled:opacity-60"
          >
            {loading ? "Signing in..." : "Login"}
          </button>
        </form>

        <div className="text-xs text-gray-500 text-center mt-4">Authorized personnel only</div>
      </div>
    </div>
  );
}
