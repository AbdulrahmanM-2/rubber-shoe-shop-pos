export default function Receipt({ sale }) {
  if (!sale) return null;

  const total = sale.items.reduce(
    (sum, i) => sum + i.unitPrice * i.quantity,
    0
  );

  return (
    <div id="receipt" style={{ border: "1px solid black", padding: 10 }}>
      <h3>Rubber Shoes Shop</h3>
      <p>Sale No: {sale.saleNo}</p>
      <p>Payment: {sale.paymentType}</p>
      <table width="100%">
        <thead>
          <tr>
            <th>Product</th>
            <th>Size</th>
            <th>Qty</th>
            <th>Total</th>
          </tr>
        </thead>
        <tbody>
          {sale.items.map((i, idx) => (
            <tr key={idx}>
              <td>{i.variant.product.name}</td>
              <td>{i.variant.size}/{i.variant.color}</td>
              <td>{i.quantity}</td>
              <td>{i.unitPrice * i.quantity} Tsh</td>
            </tr>
          ))}
        </tbody>
      </table>
      <h4>Total: {total} Tsh</h4>
      <button onClick={() => window.print()}>Print Receipt</button>
    </div>
  );
}
