const PaymentPanel = ({ paymentAmount, setPaymentAmount, paymentMethod, setPaymentMethod, addPayment, messages }) => (
  <div className="payment-panel">
    <h3>{messages.addPayment}</h3>
    <input
      type="number"
      min="0"
      value={paymentAmount}
      onChange={(e) => setPaymentAmount(parseFloat(e.target.value))}
      placeholder={messages.paymentAmount}
    />
    <select value={paymentMethod} onChange={(e) => setPaymentMethod(e.target.value)}>
      <option value="CASH">{messages.cash}</option>
      <option value="MOBILE_MONEY">{messages.mobileMoney}</option>
      <option value="CREDIT">{messages.credit}</option>
    </select>
    <button onClick={addPayment}>{messages.addPayment}</button>
  </div>
);

export default PaymentPanel;
