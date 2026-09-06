import React from "react";
import "./Contact.css";
import logo from "../assets/MainLogo1.png";
import { FaEnvelope, FaPhoneAlt, FaMapMarkerAlt } from "react-icons/fa";
import { Link } from "react-router-dom";

const Contact = () => {
  return (
    <div className="contact-wrapper">
      <div className="contact-header">
        <img src={logo} alt="Flora Harbor Logo" className="contact-logo" />
        <h1>Get in Touch</h1>
        <p>Your happiness is our priority. Reach out anytime!</p>
      </div>

      <div className="contact-cards">

        <div className="contact-card">
          <FaEnvelope className="contact-icon" />
          <h3>Email Us</h3>
          <p>support@floraharbor.com</p>
        </div>

        <div className="contact-card">
          <FaPhoneAlt className="contact-icon" />
          <h3>Call Us</h3>
          <p>+91 98765 43210</p>
        </div>

        <div className="contact-card">
          <FaMapMarkerAlt className="contact-icon" />
          <h3>Visit Us</h3>
          <p>Hyderabad, India</p>
        </div>

    
      </div>
      {/* Button goes here, outside the cards */}
<Link to="/" className="order-button">Go to Home</Link>
    </div>
  );
};

export default Contact;