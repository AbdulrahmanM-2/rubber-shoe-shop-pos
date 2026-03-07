import React from "react";

function Tables({ recentOrders, topProducts, lowStock, activityLog }) {
  return (
    <section className="grid grid-cols-1 lg:grid-cols-2 gap-4 my-4">

      {/* Recent Orders */}
      <div className="card bg-white p-4 rounded-lg shadow">
        <h3 className="font-semibold mb-2">Recent Orders</h3>
        <table className="w-full text-left border-collapse">
          <thead>
            <tr className="border-b">
              <th className="py-2">Order #</th>
              <th className="py-2">Amount</th>
            </tr>
          </thead>
          <tbody>
            {recentOrders.map((order, i) => (
              <tr key={i} className="border-b hover:bg-gray-50">
                <td className="py-2">{order.id}</td>
                <td className="py-2">{order.amount}</td>
              </tr>
            ))}
          </tbody>
        </table>
        <a className="text-blue-600 text-sm mt-2 inline-block">View All →</a>
      </div>

      {/* Top Products */}
      <div className="card bg-white p-4 rounded-lg shadow">
        <h3 className="font-semibold mb-2">Top Selling Products</h3>
        <ul className="list-disc pl-5">
          {topProducts.map((p, i) => (
            <li key={i}>{p.name} <strong>{p.sold}</strong> Sold</li>
          ))}
        </ul>
      </div>

      {/* Low Stock */}
      <div className="card bg-white p-4 rounded-lg shadow">
        <h3 className="font-semibold mb-2">Low Stock Alerts</h3>
        <ul className="list-disc pl-5 text-red-600">
          {lowStock.map((item, i) => (
            <li key={i}>{item.name} <span className="text-gray-700">Only {item.remaining} Left</span></li>
          ))}
        </ul>
      </div>

      {/* Activity Log */}
      <div className="card bg-white p-4 rounded-lg shadow">
        <h3 className="font-semibold mb-2">Activity Log</h3>
        <ul className="list-disc pl-5 text-gray-700">
          {activityLog.map((log, i) => (
            <li key={i}>{log}</li>
          ))}
        </ul>
        <a className="text-blue-600 text-sm mt-2 inline-block">View All →</a>
      </div>

    </section>
  );
}

export default Tables;        <ul className="activity">
          {activityLog.map((log, i) => (<li key={i}>{log}</li>))}
        </ul>
        <a className="view">View All →</a>
      </div>
    </section>
  );
}

export default Tables;
