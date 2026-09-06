import React, { useState } from "react";
import "./SignupPage.css";
import logo from "../assets/MainLogo1.png";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const SignupPage = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [rePassword, setRePassword] = useState("");

  const navigate = useNavigate();

  const payload = {
    userName: name,
    email: email,
    password: password,
    reEnterPassword: rePassword
  };

  // ✅ Password Validation Regex
  const isStrongPassword = (password) => {
    const regex =
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#])[A-Za-z\d@$!%*?&#]{8,}$/;
    return regex.test(password);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // ✅ Step 1: Check if email already exists
    try {
      const emailCheck = await axios.get(
        `http://localhost:9000/users/check-email?email=${email}`
      );

      if (emailCheck.data.exists) {
        toast.error("User already exists! Please login.");
        return;
      }
    } catch (err) {
      console.error("Email check failed:", err);
      toast.error("Error checking email!");
      return;
    }

    // ✅ Step 2: Password match check
    if (password !== rePassword) {
      toast.warning("Passwords do not match!");
      return;
    }

    // ✅ Step 3: Strong password check
    if (!isStrongPassword(password)) {
      toast.warning(
        "Password must be at least 8 characters, with uppercase, lowercase, number and special symbol!"
      );
      return;
    }

    // ✅ Step 4: Save user
    try {
      await axios.post("http://localhost:9000/users/signup1", payload);
      toast.success("Signup successful!");
      setTimeout(() => navigate("/"), 2000);
    } catch (error) {
      console.error("Signup failed:", error);
      toast.error("Signup failed. Please try again.");
    }
  };

  return (
    <div className="signup-page">
      <ToastContainer position="top-right" autoClose={2000} />

      <div className="signup-container">
        <img src={logo} alt="Flora Harbor Logo" className="signup-logo" />

        <form onSubmit={handleSubmit}>
          <input
            type="text"
            className="input-box"
            placeholder="Enter Your Name"
            onChange={(e) => setName(e.target.value)}
            required
          />

          <input
            type="email"
            className="input-box"
            placeholder="Enter Your Email"
            onChange={(e) => setEmail(e.target.value)}
            required
          />

          <input
            type="password"
            className="input-box"
            placeholder="Enter Your Password"
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          {/* ✅ Password rules shown as bullets */}
<ul className="password-rules">
  <li>At least 8 characters</li>
  <li>Must contain one uppercase letter</li>
  <li>Must contain one lowercase letter</li>
  <li>Must contain one number</li>
  <li>Must contain one special symbol (@$!%*?&#)</li>
</ul>

          <input
            type="password"
            className="input-box"
            placeholder="Re-Enter Your Password"
            onChange={(e) => setRePassword(e.target.value)}
            required
          />

          <button className="btn" type="submit">
            Signup
          </button>
          <p className="register-text">
           Already have an account? <Link to="/login">Log In</Link>
          </p>
        </form>
      </div>
    </div>
  );
};

export default SignupPage;
