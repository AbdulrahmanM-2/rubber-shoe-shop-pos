import React from "react";

function Tables({ recentOrders, topProducts, lowStock, activityLog }) {
  return (
    <section className="tables">
      <div className="card">
        <h3>Recent Orders</h3>
        <table>
          <thead>
            <tr><th>Order #</th><th>Amount</th></tr>
          </thead>
          <tbody>
            {recentOrders.map((order, i) => (
              <tr key={i}><td>{order.id}</td><td>{order.amount}</td></tr>
            ))}
          </tbody>
        </table>
        <a className="view">View All →</a>
      </div>

      <div className="card">
        <h3>Top Selling Products</h3>
        <ul>
          {topProducts.map((p, i) => (<li key={i}>{p.name} <strong>{p.sold}</strong> Sold</li>))}
        </ul>
      </div>

      <div className="card">
        <h3>Low Stock Alerts</h3>
        <ul className="alert">
          {lowStock.map((item, i) => (<li key={i}>{item.name} <span>Only {item.remaining} Left</span></li>))}
        </ul>
      </div>

      <div className="card">
        <h3>Activity Log</h3>
        <ul className="activity">
          {activityLog.map((log, i) => (<li key={i}>{log}</li>))}
        </ul>
        <a className="view">View All →</a>
      </div>
    </section>
  );
}

export default Tables;
