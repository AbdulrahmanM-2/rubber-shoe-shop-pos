import { useState } from "react";

const formatTsh = (amount) =>
  new Intl.NumberFormat("sw-TZ", {
    style: "currency",
    currency: "TZS",
    minimumFractionDigits: 0,
  }).format(amount);

export default function Checkout({ cartItems, clearCart }) {
  const [paymentType, setPaymentType] = useState("CASH");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState(null);

  const total = cartItems.reduce(
    (sum, item) => sum + item.unitPrice * item.quantity,
    0
  );

  const handleCheckout = async () => {
    if (cartItems.length === 0) {
      setMessage({ type: "error", text: "Cart is empty" });
      return;
    }

    setLoading(true);
    setMessage(null);

    try {
      const res = await fetch("/api/sales", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          paymentType,
          items: cartItems.map((item) => ({
            variantId: item.variantId,
            quantity: item.quantity,
          })),
        }),
      });

      if (!res.ok) throw new Error("Checkout failed");

      const data = await res.json();
      setMessage({ type: "success", text: `Sale processed: ${data.saleNo}` });
      clearCart();
    } catch (err) {
      console.error(err);
      setMessage({ type: "error", text: err.message });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="border rounded-lg p-4 shadow flex flex-col h-full">
      <h2 className="text-lg font-semibold mb-2">Checkout</h2>

      <div className="flex flex-col gap-2 mb-4">
        <label className="text-sm font-medium">Payment Type:</label>
        <select
          value={paymentType}
          onChange={(e) => setPaymentType(e.target.value)}
          className="border rounded px-2 py-1"
        >
          <option value="CASH">Cash</option>
          <option value="MOBILE_MONEY">Mobile Money</option>
          <option value="BANK">Bank</option>
        </select>
      </div>

      <div className="mb-4 flex justify-between font-bold text-gray-800">
        <span>Total:</span>
        <span>{formatTsh(total)}</span>
      </div>

      <button
        onClick={handleCheckout}
        disabled={loading || cartItems.length === 0}
        className={`py-2 rounded font-semibold text-white ${
          loading || cartItems.length === 0
            ? "bg-gray-400 cursor-not-allowed"
            : "bg-green-600 hover:bg-green-700"
        }`}
      >
        {loading ? "Processing..." : "Process Sale"}
      </button>

      {message && (
        <p
          className={`mt-2 text-sm ${
            message.type === "error" ? "text-red-600" : "text-green-600"
          }`}
        >
          {message.text}
        </p>
      )}
    </div>
  );
        }      };

      const res = await fetch("/api/sales", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
      });

      if (!res.ok) {
        const msg = await res.text();
        throw new Error(msg || "Payment failed");
      }

      await res.json(); // SaleDto returned
      clearCart();
      alert("Payment successful ✅");

    } catch (err) {
      console.error(err);
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="bg-white shadow rounded-lg p-4">
      <h2 className="text-lg font-semibold mb-4">Checkout</h2>

      {/* Total */}
      <div className="flex justify-between mb-4 text-lg font-bold">
        <span>Total</span>
        <span>{formatTsh(total)}</span>
      </div>

      {/* Payment type */}
      <div className="mb-4">
        <label className="block text-sm font-medium mb-1">
          Payment Method
        </label>
        <select
          value={paymentType}
          onChange={e => setPaymentType(e.target.value)}
          className="w-full border rounded px-3 py-2"
        >
          <option value="CASH">Cash</option>
          <option value="MOBILE_MONEY">Mobile Money</option>
          <option value="BANK">Bank</option>
        </select>
      </div>

      {/* Error */}
      {error && (
        <div className="text-red-600 text-sm mb-2">
          {error}
        </div>
      )}

      {/* Pay button */}
      <button
        onClick={handleCheckout}
        disabled={loading}
        className={`w-full py-2 rounded text-white font-semibold ${
          loading ? "bg-gray-400" : "bg-green-600 hover:bg-green-700"
        }`}
      >
        {loading ? "Processing..." : "Pay Now"}
      </button>
    </div>
  );
          }
