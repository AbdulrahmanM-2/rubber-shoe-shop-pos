new Chart(document.getElementById("salesChart"), {
  type: "line",
  data: {
    labels: ["Mon","Tue","Wed","Thu","Fri","Sat"],
    datasets: [
      {
        label: "Sales",
        data: [10,20,15,25,22,35],
        borderColor: "#38a169",
        fill: true
      },
      {
        label: "Profit",
        data: [5,15,10,20,15,28],
        borderColor: "#3182ce",
        fill: true
      }
    ]
  }
});

new Chart(document.getElementById("stockChart"), {
  type: "doughnut",
  data: {
    labels: ["In Stock","Out of Stock"],
    datasets: [{
      data: [320,45],
      backgroundColor: ["#38a169","#e53e3e"]
    }]
  }
});
