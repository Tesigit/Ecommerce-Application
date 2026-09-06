import React from "react";
import "./Footer.css";
import logo from "../assets/MainLogo1.png";
import {  useNavigate } from "react-router-dom";

const Footer = () => {
      const navigate = useNavigate();

  const handleSubscribe = () => {
    navigate("/signup");
  };

    return (
        <footer className="fh-footer">

            {/* Top section - Newsletter */}
            <div className="footer-newsletter">
                <h2>Join Our Green Community</h2>
                <p>Get plant care tips, exclusive offers & new arrivals directly in your inbox.</p>

                <div className="newsletter-box">
                    <input type="email" placeholder="Enter your email" />
                    
                            <button onClick={handleSubscribe}>Subscribe</button>

                </div>
            </div>

            {/* Middle Grid */}
            <div className="footer-grid">

                {/* Logo + About */}
                <div className="footer-col">
                    <img src={logo} alt="Flora Harbor Logo" className="footer-logo" />
                    <p className="footer-about">
                        Flora Harbor is your trusted place to buy healthy indoor & outdoor plants.
                        We grow, care, and deliver plants with love.
                    </p>

                    {/* Social Icons */}
                    <div className="social-icons">
                        <a href="#"><i className="fab fa-facebook"></i></a>
                        <a href="#"><i className="fab fa-instagram"></i></a>
                        <a href="#"><i className="fab fa-twitter"></i></a>
                        <a href="#"><i className="fab fa-youtube"></i></a>
                    </div>
                </div>

                {/* Quick Links */}
                <div className="footer-col">
                    <h3>Quick Links</h3>
                    <ul>
                        <li><a href="/">Home</a></li>
                        <li><a href="/plants">All Plants</a></li>
                        <li><a href="/indoor">Indoor Plants</a></li>
                        <li><a href="/outdoor">Outdoor Plants</a></li>
                        <li><a href="/contact">Contact Us</a></li>
                        <li><a href="/about">About Us</a></li>
                    </ul>
                </div>

                {/* Support */}
                <div className="footer-col">
                    <h3>Customer Support</h3>
                    <ul>
                        <li><a href="/faq">FAQs</a></li>
                        <li><a href="/shipping">Shipping Policy</a></li>
                        <li><a href="/return">Return Policy</a></li>
                        <li><a href="/privacy">Privacy Policy</a></li>
                        <li><a href="/terms">Terms & Conditions</a></li>
                    </ul>
                </div>

                {/* Contact Section */}
                <div className="footer-col">
                    <h3>Reach Us</h3>
                    <p>Email: support@floraharbor.com</p>
                    <p>Phone: +91 98765 43210</p>
                    <p>Location: Hyderabad, India</p>

                    <h3 className="hours-title">Opening Hours</h3>
                    <p>Mon – Sat: 9:30 AM – 7:00 PM</p>
                    <p>Sunday: Closed</p>
                </div>
            </div>

            {/* Copyright */}
            <div className="footer-bottom">
                © {new Date().getFullYear()} Flora Harbor — All Rights Reserved.
            </div>
        </footer>
    );
};

export default Footer;