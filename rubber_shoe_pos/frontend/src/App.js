import React from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import ProductsTable from "./components/ProductsTable";
import Checkout from "./components/Checkout";

function Dashboard() {
  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-6 text-gray-800">
        Rubber Shoe Shop POS Dashboard
      </h1>
      <div className="grid grid-cols-2 gap-6">
        <div>
          <h2 className="text-lg font-semibold mb-2">Products</h2>
          <ProductsTable />
        </div>
        <div>
          <Checkout />
        </div>
      </div>
    </div>
  );
}

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Default route */}
        <Route path="/" element={<Dashboard />} />

        {/* Other pages */}
        <Route path="/checkout" element={<Checkout />} />
        <Route path="/products" element={<ProductsTable />} />

        {/* Fallback: redirect unknown routes to dashboard */}
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </BrowserRouter>
  );
}
