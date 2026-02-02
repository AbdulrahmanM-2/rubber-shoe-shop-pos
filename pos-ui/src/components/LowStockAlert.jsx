import { useEffect, useState } from "react";
import { api } from "../services/api";

export default function LowStockAlert() {
  const [lowStock, setLowStock] = useState([]);

  useEffect(() => {
    fetchLowStock();
  }, []);

  const fetchLowStock = async () => {
    const { data } = await api.get("/reports/low-stock");
    setLowStock(data);
  };

  if (!lowStock.length) return null;

  return (
    <div style={{ background: "#ffcccc", padding: 10, marginBottom: 10 }}>
      <h4>⚠️ Low Stock Alert</h4>
      {lowStock.map((v) => (
        <div key={v.id}>
          {v.product.name} ({v.size}/{v.color}) – {v.quantity} left
        </div>
      ))}
    </div>
  );
}
