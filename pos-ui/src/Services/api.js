import axios from "axios";

const API_URL = "http://localhost:8080/api";

export const api = axios.create({
  baseURL: API_URL,
});

export const setToken = (token) => {
  api.defaults.headers.common["Authorization"] = `Bearer ${token}`;
};

// Auth
export const login = (username, password) =>
  api.post("/auth/login", { username, password });

// Products & variants
export const getProducts = () => api.get("/products");
export const getVariants = () => api.get("/variants");

// Sales
export const createSale = (saleRequest) => api.post("/sales", saleRequest);
