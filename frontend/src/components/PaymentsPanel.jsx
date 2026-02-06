import React from "react";

const PaymentPanel = ({ paymentAmount, setPaymentAmount, paymentMethod, setPaymentMethod, addPayment }) => {
  return (
    <div className="payment-panel">
      <h3>Add Payment</h3>
      <input
        type="number"
        min="0"
        value={paymentAmount}
        onChange={(e) => setPaymentAmount(parseFloat(e.target.value))}
      />
      <select value={paymentMethod} onChange={(e) => setPaymentMethod(e.target.value)}>
        <option value="CASH">Cash</option>
        <option value="MOBILE_MONEY">Mobile Money</option>
        <option value="CREDIT">Credit</option>
      </select>
      <button onClick={addPayment}>Add Payment</button>
    </div>
  );
};

export default PaymentPanel;
