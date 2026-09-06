import React from "react";
import { Link } from "react-router-dom";
import "./OrderSuccess.css";

const OrderSuccess = () => {
  return (
    <div className="order-container">
      <h1 className="order-heading">🎉 Order Placed Successfully!</h1>
      <p className="order-message">
        Thank you for your purchase. Your order has been received and is being processed.
      </p>
      <Link to="/" className="order-button">Go to Home</Link>
    </div>
  );
};

export default OrderSuccess;