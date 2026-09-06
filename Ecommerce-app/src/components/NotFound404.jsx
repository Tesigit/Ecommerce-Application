import React from "react";
import "./NotFound404.css";
import { Link } from "react-router-dom";

const NotFound404 = () => {
  return (
    <div className="nf-body">
      <div className="wrapper">
        <img
          className="leaf"
          src="https://cdn-icons-png.flaticon.com/512/3627/3627692.png"
          alt="leaf"
        />

        <h1>404</h1>

        <p>Oops! This plant page isn’t growing here 🌱</p>

        <Link to="/" className="btn">
          Return Home
        </Link>
      </div>
    </div>
  );
};

export default NotFound404;
