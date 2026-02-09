import React from "react";

function Metrics({ metricsData }) {
  return (
    <section className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4 my-4">
      {metricsData.map((metric, i) => (
        <div
          key={i}
          className={`p-4 rounded-lg text-white shadow-md ${
            metric.color === "green"
              ? "bg-green-500"
              : metric.color === "blue"
              ? "bg-blue-500"
              : metric.color === "orange"
              ? "bg-orange-500"
              : metric.color === "purple"
              ? "bg-purple-500"
              : "bg-gray-500"
          }`}
        >
          <h4 className="text-sm font-medium">{metric.title}</h4>
          <h2 className="text-2xl font-bold mt-1">{metric.value}</h2>
        </div>
      ))}
    </section>
  );
}

export default Metrics;
