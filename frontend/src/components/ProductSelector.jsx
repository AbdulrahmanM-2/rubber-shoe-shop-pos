import React from "react";

const ProductSelector = ({ products, selectedProduct, setSelectedProduct, quantity, setQuantity, addItem }) => {
  return (
    <div className="product-selector">
      <h3>Add Product</h3>
      <select
        value={selectedProduct?.id || ""}
        onChange={(e) =>
          setSelectedProduct(products.find((p) => p.id === parseInt(e.target.value)))
        }
      >
        <option value="">--Select Product--</option>
        {products.map((p) => (
          <option key={p.id} value={p.id}>
            {p.name} | {p.color} | Size: {p.size} | Stock: {p.stock} | Price: {p.unitPrice}
          </option>
        ))}
      </select>
      <input
        type="number"
        min="1"
        value={quantity}
        onChange={(e) => setQuantity(parseInt(e.target.value))}
      />
      <button onClick={addItem}>Add Item</button>
    </div>
  );
};

export default ProductSelector;
