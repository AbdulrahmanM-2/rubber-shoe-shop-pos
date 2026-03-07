import { Navigate, Outlet } from "react-router-dom";

/**
 * Protects routes that require CASHIER role
 * Cashiers AND Managers are allowed (manager can operate POS)
 */
export default function RequireCashier() {
  const user = JSON.parse(localStorage.getItem("user"));

  // Not logged in
  if (!user) {
    return <Navigate to="/login" replace />;
  }

  // Not allowed to use POS
  if (user.role !== "CASHIER" && user.role !== "MANAGER") {
    return (
      <div className="h-screen flex items-center justify-center bg-gray-100">
        <div className="bg-white p-6 rounded shadow text-center">
          <h2 className="text-xl font-semibold mb-2">Access Denied</h2>
          <p className="text-gray-600 mb-4">
            You do not have permission to access the POS system.
          </p>
          <button
            onClick={() => window.history.back()}
            className="px-4 py-2 bg-blue-600 text-white rounded"
          >
            Go Back
          </button>
        </div>
      </div>
    );
  }

  // Authorized
  return <Outlet />;
}
