import React from "react";

function Metrics({ metricsData }) {
  return (
    <section className="metrics">
      {metricsData.map((metric, i) => (
        <div key={i} className={`metric ${metric.color}`}>
          <h4>{metric.title}</h4>
          <h2>{metric.value}</h2>
        </div>
      ))}
    </section>
  );
}

export default Metrics;
