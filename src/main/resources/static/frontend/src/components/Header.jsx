import React from "react";

export default function Header() {
  return (
    <header className="bg-primary text-white flex items-center justify-between px-6 py-4 shadow-md">
      <div className="flex items-center gap-3">
        <img src="/logo.png" alt="Timeless Shoes" className="h-10 w-10"/>
        <span className="text-2xl font-logo">Timeless Shoes</span>
      </div>
      <nav className="flex gap-4">
        <a href="/dashboard" className="hover:underline">Dashboard</a>
        <a href="/customers" className="hover:underline">Customers</a>
        <a href="/orders" className="hover:underline">Orders</a>
        <a href="/payments" className="hover:underline">Payments</a>
        <a href="/profile" className="hover:underline">Profile</a>
      </nav>
    </header>
  );
}
