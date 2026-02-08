import { useEffect, useState } from "react";
import { getActiveShift, startShift, endShift } from "../services/shiftApi";
import { createOrder } from "../services/orderApi";
import StartShiftModal from "../components/StartShiftModal";
import EndShiftModal from "../components/EndShiftModal";
import ShiftMetrics from "../components/ShiftMetrics";

const products = [
  { id: 1, name: "Rubber Shoes", price: 50 },
  { id: 2, name: "Socks", price: 5 },
  { id: 3, name: "Cleaning Service", price: 10 },
];

export default function POS() {
  const [activeShift, setActiveShift] = useState(null);
  const [showEndModal, setShowEndModal] = useState(false);
  const [cart, setCart] = useState([]);

  const loadShift = () => getActiveShift().then(setActiveShift);
  useEffect(loadShift, []);

  if (!activeShift) {
    return <StartShiftModal onStart={(cash) => startShift(cash).then(loadShift)} />;
  }

  const addToCart = (product) => setCart([...cart, product]);
  const removeFromCart = (index) => {
    const newCart = [...cart];
    newCart.splice(index, 1);
    setCart(newCart);
  };

  const total = cart.reduce((sum, p) => sum + p.price, 0);

  const submitSale = async () => {
    if (cart.length === 0) return alert("Add at least one product!");
    await createOrder(activeShift.id, cart);
    setCart([]);
    loadShift();
  };

  return (
    <div className="p-6 space-y-6">
      <div className="bg-white p-4 rounded shadow flex justify-between items-center">
        <div>
          <p><strong>Cashier:</strong> {activeShift.cashier.username}</p>
          <p><strong>Opening Cash:</strong> ${activeShift.openingCash.toFixed(2)}</p>
          <ShiftMetrics shiftId={activeShift.id} />
        </div>
        <button
          className="bg-red-600 text-white px-4 py-2 rounded"
          onClick={() => setShowEndModal(true)}
        >
          End Shift
        </button>
      </div>

      <div className="bg-white p-4 rounded shadow space-y-2">
        <h2 className="font-bold text-lg mb-2">Products</h2>
        <div className="grid grid-cols-3 gap-2">
          {products.map(p => (
            <button
              key={p.id}
              className="bg-blue-600 text-white p-2 rounded hover:bg-blue-700"
              onClick={() => addToCart(p)}
            >
              {p.name} - ${p.price}
            </button>
          ))}
        </div>
      </div>

      <div className="bg-white p-4 rounded shadow">
        <h2 className="font-bold text-lg mb-2">Current Order</h2>
        {cart.length === 0 ? <p className="text-gray-500">No items yet</p> : (
          <table className="w-full text-sm">
            <thead className="bg-gray-50">
              <tr>
                <th className="p-2 text-left">Product</th>
                <th className="p-2 text-left">Price</th>
                <th className="p-2">Remove</th>
              </tr>
            </thead>
            <tbody>
              {cart.map((item, i) => (
                <tr key={i} className="border-t">
                  <td className="p-2">{item.name}</td>
                  <td className="p-2">${item.price}</td>
                  <td className="p-2 text-center">
                    <button className="text-red-600" onClick={() => removeFromCart(i)}>X</button>
                  </td>
                </tr>
              ))}
              <tr className="font-bold border-t">
                <td className="p-2">Total</td>
                <td className="p-2">${total.toFixed(2)}</td>
                <td></td>
              </tr>
            </tbody>
          </table>
        )}
        <button
          className="mt-2 bg-green-600 text-white px-4 py-2 rounded"
          onClick={submitSale}
        >
          Submit Sale
        </button>
      </div>

      {showEndModal && (
        <EndShiftModal
          onEnd={(cash) => endShift(cash).then(() => { setShowEndModal(false); loadShift(); })}
        />
      )}
    </div>
  );
    }
