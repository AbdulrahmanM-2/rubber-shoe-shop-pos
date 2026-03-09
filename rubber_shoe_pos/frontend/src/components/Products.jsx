import React from "react";
import ProductsTable from "./ProductsTable";

export default function Products({ products, addToCart }) {
  if (!products) return null;

  return (
    <div className="products-wrapper">
      <ProductsTable products={products} addToCart={addToCart} />
    </div>
  );
}
