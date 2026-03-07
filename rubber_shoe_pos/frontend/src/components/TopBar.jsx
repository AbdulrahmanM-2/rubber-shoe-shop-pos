import React from "react";
import { ReactComponent as Logo } from "../assets/logo.svg";

function TopBar({ user = "Admin User", role = "OWNER" }) {
  return (
    <header className="flex justify-between items-center p-4 bg-white shadow-md border-b border-gray-200">
      
      {/* LEFT: Logo + App Name */}
      <div className="flex items-center space-x-3">
        <Logo className="w-12 h-12" /> {/* 48px x 48px */}
        <span className="text-xl font-semibold text-gray-800">
          Timeless Shoes <strong className="font-bold">POS System</strong>
        </span>
      </div>

      {/* RIGHT: User Info */}
      <div className="flex items-center space-x-3">
        <div className="text-right">
          <div className="text-gray-800 font-medium">{user}</div>
          <div className="text-sm text-white bg-blue-600 rounded px-2 py-0.5">{role}</div>
        </div>
        <img
          className="w-8 h-8 rounded-full border border-gray-300"
          src="https://i.pravatar.cc/32"
          alt="User Avatar"
        />
      </div>

    </header>
  );
}

export default TopBar;
