import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";

/* Pages */
import LoginPage from "./pages/LoginPage";
import DashboardPage from "./pages/DashboardPage"; // Manager dashboard
import POS from "./pages/POS";
import Users from "./pages/Users";

/* Route Guards */
import RequireManager from "./components/RequireManager";
import RequireCashier from "./components/RequireCashier";

function App() {
  const user = JSON.parse(localStorage.getItem("user"));
  const isAuthenticated = !!user;

  return (
    <Router>
      <Routes>

        {/* Login */}
        <Route
          path="/"
          element={
            isAuthenticated ? <Navigate to="/pos" /> : <LoginPage />
          }
        />

        {/* POS (Cashier + Manager) */}
        <Route element={<RequireCashier />}>
          <Route path="/pos" element={<POS />} />
        </Route>

        {/* Manager Routes */}
        <Route element={<RequireManager />}>
          <Route path="/dashboard" element={<DashboardPage />} />
          <Route path="/users" element={<Users />} />
        </Route>

        {/* Fallback */}
        <Route path="*" element={<Navigate to="/" />} />

      </Routes>
    </Router>
  );
}

export default App;
