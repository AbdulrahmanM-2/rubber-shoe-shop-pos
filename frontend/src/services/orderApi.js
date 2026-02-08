const BASE = "http://localhost:8080/api/orders";
export const createOrder = (shiftId, products) =>
  fetch(BASE, { method: "POST", headers: { "Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}` }, body: JSON.stringify({ shiftId, items: products.map(p=>({productId:p.id, price:p.price})) }) });
