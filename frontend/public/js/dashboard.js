<script src="https://cdn.jsdelivr.net/npm/sockjs-client/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs/lib/stomp.min.js"></script>
const socket = new SockJS("/ws");
const stomp = Stomp.over(socket);

stomp.connect({}, () => {
  stomp.subscribe("/topic/dashboard", msg => {
    const data = JSON.parse(msg.body);

    document.getElementById("todaySales").innerText =
      `Tsh ${data.todaySales.toLocaleString()}`;

    document.getElementById("todayProfit").innerText =
      `Tsh ${data.todayProfit.toLocaleString()}`;

    document.getElementById("lowStock").innerText =
      `${data.lowStockItems} Items`;

    document.getElementById("customers").innerText =
      data.totalCustomers;
  });
});
const API = "/api/dashboard";

/* METRICS */
fetch(`${API}/metrics`)
  .then(res => res.json())
  .then(d => {
    document.querySelector(".green h2").innerText = `KSh ${d.todaySales}`;
    document.querySelector(".blue h2").innerText = `KSh ${d.todayProfit}`;
    document.querySelector(".orange h2").innerText = `${d.lowStockItems} Items`;
    document.querySelector(".purple h2").innerText = d.totalCustomers;
  });

/* SALES CHART */
fetch("/api/dashboard/sales-chart")
  .then(res => res.json())
  .then(d => {
    new Chart(document.getElementById("salesChart"), {
      type: "line",
      data: {
        labels: [...d.labels],
        datasets: [
          {
            label: "Sales",
            data: [...d.sales],
            borderColor: "#38a169",
            backgroundColor: "rgba(56,161,105,0.2)",
            tension: 0.4
          },
          {
            label: "Profit",
            data: [...d.profit],
            borderColor: "#3182ce",
            backgroundColor: "rgba(49,130,206,0.2)",
            tension: 0.4
          }
        ]
      }
    });
  });

/* STOCK CHART */
fetch(`${API}/stock-summary`)
  .then(res => res.json())
  .then(d => {
    new Chart(document.getElementById("stockChart"), {
      type: "doughnut",
      data: {
        labels: ["In Stock", "Out of Stock"],
        datasets: [{
          data: [d.inStock, d.outOfStock],
          backgroundColor: ["#38a169", "#e53e3e"]
        }]
      }
    });
  });

/* RECENT ORDERS */
fetch(`${API}/recent-orders`)
  .then(res => res.json())
  .then(orders => {
    const table = document.querySelector("table");
    table.innerHTML = "<tr><th>Order #</th><th>Amount</th></tr>";
    orders.forEach(o => {
      table.innerHTML += `
        <tr>
          <td>${o.order}</td>
          <td>KSh ${o.amount}</td>
        </tr>`;
    });
  });
