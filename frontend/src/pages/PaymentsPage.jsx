import { useState, useEffect } from "react";
import Header from "../components/Header";

export default function Payments() {
  const [payments, setPayments] = useState([]);
  const [filterDate, setFilterDate] = useState("");

  // Fetch payments from backend
  const fetchPayments = (date) => {
    const url = date ? `/api/payments?date=${date}` : "/api/payments";
    fetch(url)
      .then(res => res.json())
      .then(data => setPayments(data))
      .catch(err => console.error(err));
  };

  useEffect(() => {
    fetchPayments(filterDate);
  }, [filterDate]);

  return (
    <div className="min-h-screen bg-gray-100">
      <Header />

      <main className="p-6 max-w-6xl mx-auto">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-2xl font-bold">Payments</h1>
          <input
            type="date"
            value={filterDate}
            onChange={(e) => setFilterDate(e.target.value)}
            className="border rounded px-3 py-2"
          />
        </div>

        <div className="bg-white p-6 rounded shadow-md overflow-x-auto">
          <table className="min-w-full table-auto">
            <thead>
              <tr className="bg-gray-200 text-left">
                <th className="px-4 py-2">Payment ID</th>
                <th className="px-4 py-2">Order No</th>
                <th className="px-4 py-2">Customer</th>
                <th className="px-4 py-2">Amount (Tsh)</th>
                <th className="px-4 py-2">Payment Type</th>
                <th className="px-4 py-2">Status</th>
                <th className="px-4 py-2">Date</th>
              </tr>
            </thead>
            <tbody>
              {payments.map(payment => (
                <tr key={payment.id} className="border-b hover:bg-gray-50">
                  <td className="px-4 py-2">{payment.id}</td>
                  <td className="px-4 py-2">{payment.saleNo}</td>
                  <td className="px-4 py-2">{payment.customerName || "Walk-in"}</td>
                  <td className="px-4 py-2 font-semibold">{payment.amount.toLocaleString()} Tsh</td>
                  <td className="px-4 py-2">{payment.paymentType}</td>
                  <td className={`px-4 py-2 font-bold ${
                    payment.status === "PAID" ? "text-green-600" : "text-red-600"
                  }`}>
                    {payment.status}
                  </td>
                  <td className="px-4 py-2">{new Date(payment.createdAt).toLocaleString()}</td>
                </tr>
              ))}
              {payments.length === 0 && (
                <tr>
                  <td colSpan="7" className="text-center py-4 text-gray-500">
                    No payments found.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </main>
    </div>
  );
      }
