import React from "react";
import { ReactComponent as Logo } from "../assets/logo.svg";

function TopBar({ user, role }) {
  return (
    <header className="topbar">
      <div className="left">
        <Logo width={50} height={50} />
        <span>Timeless Shoes <strong>POS System</strong></span>
      </div>
      <div className="right">
        <span>{user}</span>
        <span className="role">{role}</span>
        <img className="avatar" src="https://i.pravatar.cc/32" alt="avatar" />
      </div>
    </header>
  );
}

export default TopBar;
