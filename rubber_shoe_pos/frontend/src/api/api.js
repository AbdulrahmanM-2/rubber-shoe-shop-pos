// src/api/api.js

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080/api";

/**
 * Fetch all products
 */
export const fetchProducts = async () => {
  const res = await fetch(`${API_BASE_URL}/products`);
  if (!res.ok) throw new Error("Failed to fetch products");
  return res.json();
};

/**
 * Create a sale
 * @param {Object} saleData - { paymentType, items: [{ variantId, quantity }] }
 */
export const createSale = async (saleData) => {
  const res = await fetch(`${API_BASE_URL}/sales`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(saleData),
  });
  if (!res.ok) throw new Error("Failed to create sale");
  return res.json();
};

/**
 * Fetch all orders
 */
export const fetchOrders = async () => {
  const res = await fetch(`${API_BASE_URL}/sales`);
  if (!res.ok) throw new Error("Failed to fetch orders");
  return res.json();
};

/**
 * Fetch all customers
 */
export const fetchCustomers = async () => {
  const res = await fetch(`${API_BASE_URL}/customers`);
  if (!res.ok) throw new Error("Failed to fetch customers");
  return res.json();
};

/**
 * Fetch all users
 */
export const fetchUsers = async () => {
  const res = await fetch(`${API_BASE_URL}/users`);
  if (!res.ok) throw new Error("Failed to fetch users");
  return res.json();
};

/**
 * Fetch user profile by ID
 */
export const fetchUserProfile = async (userId) => {
  const res = await fetch(`${API_BASE_URL}/users/${userId}`);
  if (!res.ok) throw new Error("Failed to fetch user profile");
  return res.json();
};

/**
 * Other API utilities can be added here:
 * - update stock
 * - fetch payments
 * - fetch product variants
 */
