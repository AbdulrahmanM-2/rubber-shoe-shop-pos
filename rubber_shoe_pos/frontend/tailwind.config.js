/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}"
  ],
  theme: {
    extend: {
      colors: {
        primary: "#1D4ED8",      // Deep blue for headers/buttons
        secondary: "#FBBF24",    // Accent yellow
        danger: "#DC2626",       // Red for low-stock
        success: "#16A34A"      // Green for checkout button
      },
      fontFamily: {
        sans: ["Inter", "sans-serif"],
        logo: ["Pacifico", "cursive"]  // For "Timeless Shoes"
      },
      animation: {
        pulseSlow: "pulse 2s infinite"
      }
    },
  },
  plugins: []
};
