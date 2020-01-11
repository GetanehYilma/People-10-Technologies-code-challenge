import React from "react";
import "./App.css";
import Profile from "./components/profile";
import Interest from "./components/interest";
import Order from "./components/order";

function App() {
  return (
    <div className="container">
      <h1 className="p-3 mb-2 bg-primary text-white">Customer Dashboard</h1>
      <Profile />
      <Interest />
      <Order />
    </div>
  );
}

export default App;
