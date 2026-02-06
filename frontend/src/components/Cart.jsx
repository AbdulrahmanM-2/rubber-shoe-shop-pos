import React from "react";

const formatTsh = (amount) =>
  new Intl.NumberFormat("sw-TZ", {
    style: "currency",
    currency: "TZS",
    minimumFractionDigits: 0,
  }).format(amount);

export default function Cart({ cartItems, removeFromCart }) {
  if (cartItems.length === 0) {
    return (
      <div className="border rounded-lg p-4 shadow h-full">
        <h2 className="text-lg font-semibold mb-2">Cart</h2>
        <p className="text-gray-500">Your cart is empty</p>
      </div>
    );
  }

  const total = cartItems.reduce(
    (sum, item) => sum + item.unitPrice * item.quantity,
    0
  );

  return (
    <div className="border rounded-lg p-4 shadow flex flex-col h-full">
      <h2 className="text-lg font-semibold mb-2">Cart</h2>
      <div className="flex-1 overflow-y-auto">
        {cartItems.map((item) => (
          <div
            key={item.id}
            className="flex justify-between items-center border-b py-2"
          >
            <div>
              <p className="font-semibold">{item.productName}</p>
              <p className="text-sm text-gray-600">
                {item.size} | {item.color} x {item.quantity}
              </p>
            </div>
            <div className="flex flex-col items-end">
              <span className="font-bold">{formatTsh(item.unitPrice * item.quantity)}</span>
              <button
                onClick={() => removeFromCart(item.id)}
                className="text-red-600 text-xs hover:underline mt-1"
              >
                Remove
              </button>
            </div>
          </div>
        ))}
      </div>

      <div className="mt-4 border-t pt-2 flex justify-between items-center">
        <span className="font-bold">Total:</span>
        <span className="font-bold text-blue-600">{formatTsh(total)}</span>
      </div>
    </div>
  );
      }
