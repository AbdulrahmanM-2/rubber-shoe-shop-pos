const BASE = "http://localhost:8080/api/users";
export const getUsers = () => fetch(BASE, { headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }}).then(r=>r.json());
