import { useEffect, useState } from "react";
import { getShiftMetrics } from "../services/shiftApi";

export default function ShiftMetrics({ shiftId }) {
  const [metrics, setMetrics] = useState(null);
  useEffect(() => {
    if (!shiftId) return;
    getShiftMetrics(shiftId).then(setMetrics);
  }, [shiftId]);

  if (!metrics) return <p>Loading metrics...</p>;

  return (
    <div className="mt-2 bg-gray-50 p-2 rounded space-y-1">
      <p><strong>Total Sales:</strong> ${metrics.totalSales?.toFixed(2)}</p>
      <p><strong>Closing Cash:</strong> ${metrics.closingCash?.toFixed(2)}</p>
      <p className={`font-bold ${metrics.variance < 0 ? "text-red-600" : "text-green-600"}`}>
        <strong>Variance:</strong> ${metrics.variance?.toFixed(2)}
      </p>
    </div>
  );
                                 }
