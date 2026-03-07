// src/components/ProductTable.jsx
export default function ProductTable({ products, addToCart }) {
  if (!products || products.length === 0) return <p>No products available.</p>;

  return (
    <table className="w-full table-auto border-collapse">
      <thead>
        <tr className="bg-gray-200">
          <th className="border px-2 py-1">Product</th>
          <th className="border px-2 py-1">Variant</th>
          <th className="border px-2 py-1">Color</th>
          <th className="border px-2 py-1">Size</th>
          <th className="border px-2 py-1">Stock</th>
          <th className="border px-2 py-1">Price (Tsh)</th>
          <th className="border px-2 py-1">Action</th>
        </tr>
      </thead>
      <tbody>
        {products.map((p) => (
          <tr key={p.id} className="hover:bg-gray-100">
            <td className="border px-2 py-1">{p.name}</td>
            <td className="border px-2 py-1">{p.variantName || "-"}</td>
            <td className="border px-2 py-1">{p.color}</td>
            <td className="border px-2 py-1">{p.size}</td>
            <td className="border px-2 py-1">{p.quantity}</td>
            <td className="border px-2 py-1">{p.sellingPrice.toLocaleString()} Tsh</td>
            <td className="border px-2 py-1">
              <button
                className="bg-blue-500 text-white px-2 py-1 rounded hover:bg-blue-600"
                onClick={() => addToCart(p)}
                disabled={p.quantity <= 0}
              >
                Add to Cart
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}    const socket = new SockJS("/ws");
    const client = new Client({
      webSocketFactory: () => socket,
      debug: (str) => console.log(str)
    });

    client.onConnect = () => {
      client.subscribe("/topic/stock", (message) => {
        const updatedVariant = JSON.parse(message.body);
        setProducts(prev =>
          prev.map(v => (v.id === updatedVariant.id ? updatedVariant : v))
        );
      });
    };

    client.activate();
    return () => client.deactivate();
  }, []);

  if (loading) return <p>Loading products...</p>;
  if (error) return <p className="text-red-600">{error}</p>;

  return (
    <div className="grid grid-cols-3 gap-4">
      {products.map(variant => {
        const lowStock = variant.quantity <= variant.reorderLevel;

        return (
          <div
            key={variant.id}
            className="border rounded-lg p-4 flex flex-col justify-between shadow hover:shadow-lg transition duration-200"
          >
            <div>
              <h3 className="font-semibold text-lg mb-1">{variant.product.name}</h3>
              <p className="text-sm text-gray-600 mb-1">
                Size: {variant.size} | Color: {variant.color}
              </p>
              <p className="text-gray-800 font-bold mb-2">
                {formatTsh(variant.sellingPrice)}
              </p>
              {lowStock && (
                <span className="text-xs font-semibold bg-red-100 text-red-700 px-2 py-1 rounded animate-pulse">
                  Low Stock ({variant.quantity})
                </span>
              )}
            </div>

            <button
              disabled={variant.quantity === 0}
              onClick={() => addToCart({
                id: variant.id,
                variantId: variant.id,
                productName: variant.product.name,
                size: variant.size,
                color: variant.color,
                unitPrice: variant.sellingPrice
              })}
              className={`mt-4 py-2 rounded text-white font-semibold w-full ${
                variant.quantity === 0
                  ? "bg-gray-400 cursor-not-allowed"
                  : "bg-blue-600 hover:bg-blue-700"
              }`}
            >
              {variant.quantity === 0 ? "Out of stock" : "Add to Cart"}
            </button>
          </div>
        );
      })}
    </div>
  );
}
