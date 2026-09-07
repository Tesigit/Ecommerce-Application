
import React, { useState } from "react";
import "./LoginPage.css";
import logo from "../assets/MainLogo1.png"; // same logo
import { Link } from "react-router-dom";
import axios from "axios";
import { useNavigate } from "react-router-dom";

import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const LoginPage = () => {
  const [form, setForm] = useState({
    email: "",
    password: ""
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const navigate = useNavigate();

  const handleSubmit = (e) => {
  e.preventDefault();

  axios.post(
  "https://ecommerce-application-production-f58b.up.railway.app/users/login1",
  form
)
    .then((res) => {
      if (res.data === true) {
        // ✅ SUCCESS: show toast and store user info
        toast.success("Login Successful!", { autoClose: 2000 });

        localStorage.setItem("user", JSON.stringify({
          email: form.email,
          avatar: `https://ui-avatars.com/api/?name=${form.email}&background=random`
        }));

        setTimeout(() => navigate("/"), 2000);
      } else {
        toast.error("Invalid email or password");
      }
    })
    .catch((error) => {
      console.error("Login error:", error);
      toast.error("Something went wrong. Try again.");
    });
};


  return (
    <div className="login-page">
       <ToastContainer position="top-right" autoClose={2000} />
      <div className="login-container">

        {/* ---- Logo at top ---- */}
        <img 
          src={logo} 
          alt="Flora Harbor Logo"
          className="login-logo"
        />

        <form onSubmit={handleSubmit}>
          <input
            type="email"
            name="email"
            className="input-box"
            placeholder="Enter Your Email"
            value={form.email}
            onChange={handleChange}
            required
          />

          <input
            type="password"
            name="password"
            className="input-box"
            placeholder="Enter Your Password"
            value={form.password}
            onChange={handleChange}
            required
          />

          <button className="btn" type="submit">
            Login
          </button>

          <br/>
          <p className="register-text">
           Don’t have an account? <Link to="/signup">Create one</Link>
          </p>

        </form>

      </div>
    </div>
  );
};

export default LoginPage;
