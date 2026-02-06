const ProductSelector = ({ products, selectedProduct, setSelectedProduct, quantity, setQuantity, addItem, messages }) => (
  <div className="space-y-2">
  <h3 className="text-lg font-semibold">{messages.addProduct}</h3>
  <select
    className="w-full border rounded px-2 py-1"
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
    className="w-full border rounded px-2 py-1"
    value={quantity}
    onChange={(e) => setQuantity(parseInt(e.target.value))}
  />
  <button
    className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700"
    onClick={addItem}
  >
    {messages.addItem}
  </button>
</div>
);

export default ProductSelector;
