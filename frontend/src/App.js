import React from "react";
import { useDashboardData } from "./hooks/useDashboardData";
import TopBar from "./components/TopBar";
import Metrics from "./components/Metrics";
import Charts from "./components/Charts";
import Tables from "./components/Tables";
import "./css/dashboard.css";

function App() {
  const {
    metrics,
    sales,
    stock,
    recentOrders,
    topProducts,
    lowStock,
    activityLog,
    user,
    loading,
  } = useDashboardData();

  if (loading) return <div className="loading">Loading dashboard...</div>;

  return (
    <div className="App">
      <TopBar user={user.name} role={user.role} />
      <main className="container">
        <Metrics metricsData={metrics} />
        <Charts salesData={sales} stockData={stock} />
        <Tables
          recentOrders={recentOrders}
          topProducts={topProducts}
          lowStock={lowStock}
          activityLog={activityLog}
        />
      </main>
    </div>
  );
}

export default App;
