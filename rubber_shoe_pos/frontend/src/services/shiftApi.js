const BASE = "http://localhost:8080/api/shifts";
export const getActiveShift = () => fetch(`${BASE}/active`, { headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }}).then(r => r.json());
export const startShift = (cash) => fetch(`${BASE}/start`, { method:"POST", headers:{"Content-Type":"application/json","Authorization":`Bearer ${localStorage.getItem("token")}`}, body:JSON.stringify({openingCash: cash})});
export const endShift = (cash) => fetch(`${BASE}/end`, { method:"POST", headers:{"Content-Type":"application/json","Authorization":`Bearer ${localStorage.getItem("token")}`}, body:JSON.stringify({closingCash: cash})});
export const getShifts = (filter) => fetch(`${BASE}?period=${filter}`, { headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }}).then(r=>r.json());
export const getShiftMetrics = (shiftId) => fetch(`${BASE}/${shiftId}/metrics`, { headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }}).then(r=>r.json());
