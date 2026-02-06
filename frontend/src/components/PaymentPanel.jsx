const PaymentPanel = ({ paymentAmount, setPaymentAmount, paymentMethod, setPaymentMethod, addPayment, messages }) => (
  <div className="space-y-2">
  <h3 className="text-lg font-semibold">{messages.addPayment}</h3>
  <input
    type="number"
    min="0"
    value={paymentAmount}
    onChange={(e) => setPaymentAmount(parseFloat(e.target.value))}
    placeholder={messages.paymentAmount}
    className="w-full border rounded px-2 py-1"
  />
  <select
    value={paymentMethod}
    onChange={(e) => setPaymentMethod(e.target.value)}
    className="w-full border rounded px-2 py-1"
  >
    <option value="CASH">{messages.cash}</option>
    <option value="MOBILE_MONEY">{messages.mobileMoney}</option>
    <option value="CREDIT">{messages.credit}</option>
  </select>
  <button
    className="w-full bg-green-600 text-white py-2 rounded hover:bg-green-700"
    onClick={addPayment}
  >
    {messages.addPayment}
  </button>
</div>
);

export default PaymentPanel;
