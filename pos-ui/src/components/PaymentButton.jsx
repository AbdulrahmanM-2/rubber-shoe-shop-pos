export default function PaymentButtons({ onPay }) {
  return (
    <div>
      <h3>Payment</h3>
      <button onClick={() => onPay("CASH")}>Cash</button>
      <button onClick={() => onPay("MOBILE")}>Mobile Money</button>
      <button onClick={() => onPay("CREDIT")}>Credit</button>
    </div>
  );
}
