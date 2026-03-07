import { useState } from "react";
export default function StartShiftModal({ onStart }) {
  const [cash, setCash] = useState("");
  return (
    <div className="fixed inset-0 bg-black/40 flex items-center justify-center">
      <div className="bg-white p-6 rounded w-96 space-y-4">
        <h2 className="font-bold text-lg">Start Shift</h2>
        <input type="number" placeholder="Opening cash" className="w-full border p-2 rounded"
          onChange={e => setCash(e.target.value)} />
        <div className="flex justify-end gap-2">
          <button onClick={() => onStart(cash)} className="bg-blue-600 text-white px-3 py-1 rounded">Start</button>
        </div>
      </div>
    </div>
  );
}
