import { useState, useEffect } from "react";
import Header from "../components/Header";

export default function Orders() {
  const [orders, setOrders] = useState([]);
  const [selectedOrder, setSelectedOrder] = useState(null);

  // Fetch all orders
  useEffect(() => {
    fetch("/api/sales") // Replace with your backend endpoint
      .then(res => res.json())
      .then(data => setOrders(data))
      .catch(err => console.error(err));
  }, []);

  const handleViewDetails = (order) => {
    setSelectedOrder(order);
  };

  const handleCloseDetails = () => {
    setSelectedOrder(null);
  };

  const handleDelete = (id) => {
    if (!confirm("Are you sure you want to delete this order?")) return;

    fetch(`/api/sales/${id}`, { method: "DELETE" })
      .then(() => setOrders(prev => prev.filter(o => o.saleId !== id)))
      .catch(err => console.error(err));
  };

  const handleMarkPaid = (id) => {
    fetch(`/api/sales/${id}/pay`, { method: "POST" }) // Example endpoint
      .then(res => res.json())
      .then(updated => {
        setOrders(prev => prev.map(o => o.saleId === id ? updated : o));
      })
      .catch(err => console.error(err));
  };

  return (
    <div className="min-h-screen bg-gray-100">
      <Header />

      <main className="p-6 max-w-6xl mx-auto">
        <h1 className="text-2xl font-bold mb-6">Orders</h1>

        <div className="bg-white p-6 rounded shadow-md overflow-x-auto">
          <table className="min-w-full table-auto">
            <thead>
              <tr className="bg-gray-200 text-left">
                <th className="px-4 py-2">Order No</th>
                <th className="px-4 py-2">Customer</th>
                <th className="px-4 py-2">Items</th>
                <th className="px-4 py-2">Total (Tsh)</th>
                <th className="px-4 py-2">Payment</th>
                <th className="px-4 py-2">Actions</th>
              </tr>
            </thead>
            <tbody>
              {orders.map(order => (
                <tr key={order.saleId} className="border-b">
                  <td className="px-4 py-2">{order.saleNo}</td>
                  <td className="px-4 py-2">{order.customerName || "Walk-in"}</td>
                  <td className="px-4 py-2">{order.items.length}</td>
                  <td className="px-4 py-2 font-semibold">{order.total.toLocaleString()} Tsh</td>
                  <td className="px-4 py-2">{order.paymentType}</td>
                  <td className="px-4 py-2 flex gap-2">
                    <button
                      onClick={() => handleViewDetails(order)}
                      className="px-2 py-1 bg-primary text-white rounded hover:bg-blue-700 text-sm"
                    >
                      View
                    </button>
                    <button
                      onClick={() => handleMarkPaid(order.saleId)}
                      className="px-2 py-1 bg-success text-white rounded hover:bg-green-700 text-sm"
                    >
                      Mark Paid
                    </button>
                    <button
                      onClick={() => handleDelete(order.saleId)}
                      className="px-2 py-1 bg-danger text-white rounded hover:bg-red-700 text-sm"
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
              {orders.length === 0 && (
                <tr>
                  <td colSpan="6" className="text-center py-4 text-gray-500">
                    No orders found.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>

        {/* Order Details Modal */}
        {selectedOrder && (
          <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
            <div className="bg-white p-6 rounded shadow-md w-11/12 max-w-2xl">
              <h2 className="text-xl font-bold mb-4">Order {selectedOrder.saleNo}</h2>
              <div className="mb-4">
                <strong>Customer:</strong> {selectedOrder.customerName || "Walk-in"}<br/>
                <strong>Payment:</strong> {selectedOrder.paymentType}<br/>
                <strong>Total:</strong> {selectedOrder.total.toLocaleString()} Tsh<br/>
                <strong>Created At:</strong> {new Date(selectedOrder.createdAt).toLocaleString()}
              </div>
              <table className="min-w-full table-auto mb-4">
                <thead>
                  <tr className="bg-gray-200 text-left">
                    <th className="px-2 py-1">Product</th>
                    <th className="px-2 py-1">Size</th>
                    <th className="px-2 py-1">Qty</th>
                    <th className="px-2 py-1">Unit Price (Tsh)</th>
                    <th className="px-2 py-1">Total (Tsh)</th>
                  </tr>
                </thead>
                <tbody>
                  {selectedOrder.items.map(item => (
                    <tr key={item.variantId} className="border-b">
                      <td className="px-2 py-1">{item.productName}</td>
                      <td className="px-2 py-1">{item.size}</td>
                      <td className="px-2 py-1">{item.quantity}</td>
                      <td className="px-2 py-1">{item.unitPrice.toLocaleString()}</td>
                      <td className="px-2 py-1">{item.total.toLocaleString()}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
              <div className="flex justify-end">
                <button
                  onClick={handleCloseDetails}
                  className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400"
                >
                  Close
                </button>
              </div>
            </div>
          </div>
        )}
      </main>
    </div>
  );
      }
