import React, { useEffect, useRef } from "react";
import Chart from "chart.js/auto";

function Charts({ salesData, stockData }) {
  const salesChartRef = useRef(null);
  const stockChartRef = useRef(null);
  const salesChartInstance = useRef(null);
  const stockChartInstance = useRef(null);

  useEffect(() => {
    if (salesChartInstance.current) salesChartInstance.current.destroy();
    if (stockChartInstance.current) stockChartInstance.current.destroy();

    if (salesChartRef.current) {
      salesChartInstance.current = new Chart(salesChartRef.current, {
        type: "bar",
        data: {
          labels: salesData.map((d) => d.month),
          datasets: [{ label: "Sales", data: salesData.map((d) => d.amount), backgroundColor: "green" }],
        },
        options: { responsive: true },
      });
    }

    if (stockChartRef.current) {
      stockChartInstance.current = new Chart(stockChartRef.current, {
        type: "line",
        data: {
          labels: stockData.map((d) => d.month),
          datasets: [{ label: "Stock", data: stockData.map((d) => d.count), backgroundColor: "blue", fill: true }],
        },
        options: { responsive: true },
      });
    }

    return () => {
      if (salesChartInstance.current) salesChartInstance.current.destroy();
      if (stockChartInstance.current) stockChartInstance.current.destroy();
    };
  }, [salesData, stockData]);

  return (
    <section className="charts">
      <div className="card">
        <h3>Sales Overview</h3>
        <canvas ref={salesChartRef}></canvas>
      </div>
      <div className="card">
        <h3>Stock Summary</h3>
        <canvas ref={stockChartRef}></canvas>
      </div>
    </section>
  );
}

export default Charts;
