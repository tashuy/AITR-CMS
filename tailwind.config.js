/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      boxShadow: {
        custom: "8px 16px 16px hsla(0, 0%, 0%, 0.25)",
      },
    },
  },
  plugins: [],
};
