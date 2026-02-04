export default function ProductSearch({ variants, addToCart }) {
  return (
    <div>
      <h3>Products</h3>
      {variants.map((v) => (
        <div key={v.id}>
          {v.product.name} - {v.size}/{v.color} - {v.sellingPrice} Tsh
          <button onClick={() => addToCart(v)}>Add</button>
        </div>
      ))}
    </div>
  );
}
