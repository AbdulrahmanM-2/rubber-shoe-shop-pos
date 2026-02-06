import { useState, useEffect } from "react";
import Products from "../components/Products";
import Cart from "../components/Cart";
import Checkout from "../components/Checkout";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

export default function DashboardPage() {
  const [cartItems, setCartItems] = useState([]);
  const [products, setProducts] = useState([]);

  // Add item to cart
  const addToCart = (product) => {
    setCartItems((prev) => {
      const existing = prev.find((i) => i.id === product.id);
      if (existing) {
        return prev.map((i) =>
          i.id === product.id ? { ...i, quantity: i.quantity + 1 } : i
        );
      }
      return [...prev, { ...product, quantity: 1 }];
    });
  };

  // Remove item from cart
  const removeFromCart = (id) => setCartItems((prev) => prev.filter((i) => i.id !== id));

  // Clear cart after checkout
  const clearCart = () => setCartItems([]);

  // Fetch initial products
  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const res = await fetch("/api/variants");
        if (!res.ok) throw new Error("Failed to fetch products");
        const data = await res.json();
        setProducts(data);
      } catch (err) {
        console.error(err);
      }
    };

    fetchProducts();
  }, []);

  // WebSocket for live stock updates
  useEffect(() => {
    const socket = new SockJS("/ws");
    const client = new Client({
      webSocketFactory: () => socket,
      debug: (str) => console.log(str),
    });

    client.onConnect = () => {
      client.subscribe("/topic/stock", (message) => {
        const updatedVariant = JSON.parse(message.body);
        setProducts((prev) =>
          prev.map((v) => (v.id === updatedVariant.id ? updatedVariant : v))
        );
      });
    };

    client.activate();
    return () => client.deactivate();
  }, []);

  return (
    <div className="min-h-screen bg-gray-100 p-4">
      {/* Header */}
      <header className="flex items-center justify-between mb-4">
        <h1 className="text-2xl font-bold text-blue-700 flex items-center gap-2">
          <img src="/logo.png" alt="Timeless Shoes" className="w-10 h-10" />
          Timeless Shoes POS
        </h1>
        <div className="text-gray-600 font-semibold">
          Cashier: <span className="text-blue-600">John Doe</span>
        </div>
      </header>

      {/* Dashboard Grid */}
      <div className="grid grid-cols-3 gap-4">
        {/* Products List */}
        <div className="col-span-2">
          <Products products={products} addToCart={addToCart} />
        </div>

        {/* Cart & Checkout */}
        <div className="col-span-1 flex flex-col gap-4">
          <Cart cartItems={cartItems} removeFromCart={removeFromCart} />
          <Checkout cartItems={cartItems} clearCart={clearCart} />
        </div>
      </div>
    </div>
  );
                    }
