const ProductSelector = ({ products, selectedProduct, setSelectedProduct, quantity, setQuantity, addItem, messages }) => (
  <div className="product-selector">
    <h3>{messages.addProduct}</h3>
    <select
      value={selectedProduct?.id || ""}
      onChange={(e) =>
        setSelectedProduct(products.find((p) => p.id === parseInt(e.target.value)))
      }
    >
      <option value="">{messages.selectProduct}</option>
      {products.map((p) => (
        <option key={p.id} value={p.id}>
          {p.name} | {p.color} | {messages.size}: {p.size} | {messages.stock}: {p.stock} | {messages.price}: {p.unitPrice}
        </option>
      ))}
    </select>
    <input
      type="number"
      min="1"
      value={quantity}
      onChange={(e) => setQuantity(parseInt(e.target.value))}
    />
    <button onClick={addItem}>{messages.addItem}</button>
  </div>
);

export default ProductSelector;
