import { useState, useEffect } from "react";
import axios from "axios";

export function useDashboardData() {
  const [metrics, setMetrics] = useState([]);
  const [sales, setSales] = useState([]);
  const [stock, setStock] = useState([]);
  const [recentOrders, setRecentOrders] = useState([]);
  const [topProducts, setTopProducts] = useState([]);
  const [lowStock, setLowStock] = useState([]);
  const [activityLog, setActivityLog] = useState([]);
  const [user, setUser] = useState({ name: "", role: "" });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [
          metricsRes,
          salesRes,
          stockRes,
          ordersRes,
          topProdRes,
          lowStockRes,
          activityRes,
          userRes,
        ] = await Promise.all([
          axios.get("/api/metrics"),
          axios.get("/api/sales"),
          axios.get("/api/stock"),
          axios.get("/api/orders/recent"),
          axios.get("/api/products/top"),
          axios.get("/api/products/low-stock"),
          axios.get("/api/activity"),
          axios.get("/api/user/current"),
        ]);

        setMetrics(metricsRes.data);
        setSales(salesRes.data);
        setStock(stockRes.data);
        setRecentOrders(ordersRes.data);
        setTopProducts(topProdRes.data);
        setLowStock(lowStockRes.data);
        setActivityLog(activityRes.data);
        setUser(userRes.data);
      } catch (error) {
        console.error("Error fetching dashboard data:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  return {
    metrics,
    sales,
    stock,
    recentOrders,
    topProducts,
    lowStock,
    activityLog,
    user,
    loading,
  };
}
