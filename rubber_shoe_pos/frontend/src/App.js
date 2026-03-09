import React from "react";
import ProductsTable from "./components/ProductsTable";
import Checkout from "./components/Checkout";

export default function App() {
  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-6 text-gray-800">
        Rubber Shoe Shop POS Dashboard
      </h1>

      {/* Dashboard layout: products + checkout */}
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
