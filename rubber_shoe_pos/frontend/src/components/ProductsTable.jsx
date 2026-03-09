import React from "react";

export default function ProductsTable({ products, addToCart }) {
  return (
    <table className="min-w-full border-collapse border border-gray-300">
      <thead>
        <tr className="bg-gray-100">
          <th className="border border-gray-300 px-4 py-2">Product</th>
          <th className="border border-gray-300 px-4 py-2">Price</th>
          <th className="border border-gray-300 px-4 py-2">Stock</th>
          <th className="border border-gray-300 px-4 py-2">Action</th>
        </tr>
      </thead>
      <tbody>
        {products.map((p) => (
          <tr key={p.id}>
            <td className="border border-gray-300 px-4 py-2">{p.name}</td>
            <td className="border border-gray-300 px-4 py-2">{p.unitPrice}</td>
            <td className="border border-gray-300 px-4 py-2">{p.stock}</td>
            <td className="border border-gray-300 px-4 py-2">
              <button
                onClick={() => addToCart(p)}
                className="bg-blue-600 text-white px-3 py-1 rounded hover:bg-blue-700"
              >
                Add to Cart
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
