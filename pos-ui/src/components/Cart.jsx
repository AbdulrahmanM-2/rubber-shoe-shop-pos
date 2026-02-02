export default function Cart({ cart }) {
  const total = cart.reduce(
    (sum, item) => sum + item.sellingPrice * item.quantity,
    0
  );

  return (
    <div>
      <h3>Cart</h3>
      {cart.map((item) => (
        <div key={item.id}>
          {item.product.name} ({item.size}/{item.color}) x {item.quantity} ={" "}
          {item.sellingPrice * item.quantity} Tsh
        </div>
      ))}
      <h4>Total: {total} Tsh</h4>
    </div>
  );
}
