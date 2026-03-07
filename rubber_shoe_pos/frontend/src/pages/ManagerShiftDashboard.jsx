import { useEffect, useState } from "react";
import { getShifts } from "../services/shiftApi";

export default function ManagerShiftDashboard() {
  const [shifts, setShifts] = useState([]);
  const [filter, setFilter] = useState("Today");

  const loadShifts = () => getShifts(filter).then(setShifts);

  useEffect(() => {
    loadShifts();
    const interval = setInterval(loadShifts, 5000);
    return () => clearInterval(interval);
  }, [filter]);

  const exportPdf = () => window.open(`http://localhost:8080/api/shifts/export/pdf?period=${filter}`, "_blank");

  return (
    <div className="p-6 space-y-4">
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold">Shift Dashboard</h1>
        <div className="flex gap-2">
          {["Today", "Week", "Month"].map(f => (
            <button
              key={f}
              onClick={() => setFilter(f)}
              className={`px-4 py-1 rounded border ${filter === f ? "bg-blue-600 text-white" : "bg-white"}`}
            >
              {f}
            </button>
          ))}
          <button onClick={exportPdf} className="px-4 py-1 bg-green-600 text-white rounded">Export PDF</button>
        </div>
      </div>

      <div className="bg-white border rounded shadow-sm overflow-x-auto">
        <table className="w-full text-sm">
          <thead className="bg-gray-50">
            <tr>
              <th className="p-3 text-left">Cashier</th>
              <th className="p-3 text-left">Start</th>
              <th className="p-3 text-left">End</th>
              <th className="p-3 text-left">Opening</th>
              <th className="p-3 text-left">Sales</th>
              <th className="p-3 text-left">Closing</th>
              <th className="p-3 text-left">Variance</th>
            </tr>
          </thead>
          <tbody>
            {shifts.map(s => (
              <tr key={s.id} className="border-t">
                <td className="p-3">{s.cashier.username}</td>
                <td className="p-3">{new Date(s.startTime).toLocaleString()}</td>
                <td className="p-3">{s.endTime ? new Date(s.endTime).toLocaleString() : "Active"}</td>
                <td className="p-3">${s.openingCash.toFixed(2)}</td>
                <td className="p-3">${s.totalSales?.toFixed(2)}</td>
                <td className="p-3">${s.closingCash?.toFixed(2)}</td>
                <td className={`p-3 font-bold ${s.variance < 0 ? "text-red-600" : "text-green-600"}`}>
                  ${s.variance?.toFixed(2)}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
              }
