import React from "react";
import "./About.css";
import { Link } from "react-router-dom";

export default function About() {
  return (
    <div className="about-wrapper">

      {/* ---------- HERO SECTION ----------- */}
      <section className="about-hero">
        <h1>About Flora Harbor</h1>
        <p className="tagline">Where Nature Meets Your Home.</p>
      </section>

      {/* ---------- OUR STORY ----------- */}
      <section className="about-section card">
        <h2>Our Story</h2>
        <p>
          Flora Harbor was created with one mission — to make greenery accessible
          to every home. We bring premium indoor & outdoor plants, quality soil 
          mixes, seeds, pots, fertilizers, and sustainable gardening essentials 
          right to your doorstep.
        </p>
      </section>

      {/* ---------- MAIN FEATURES ----------- */}
      <section className="about-features">
        <div className="feature-box">
          <span className="icon">🌱</span>
          <h3>Green Mission</h3>
          <p>Helping people create peaceful, nature-filled living spaces.</p>
        </div>

        <div className="feature-box">
          <span className="icon">♻️</span>
          <h3>Eco-Friendly Care</h3>
          <p>We follow sustainable packaging & nature-loving methods.</p>
        </div>

        <div className="feature-box">
          <span className="icon">🍃</span>
          <h3>Trusted Quality</h3>
          <p>Every plant and product is handpicked for health & freshness.</p>
        </div>
      </section>

      {/* ---------- WHAT WE OFFER ----------- */}
      <section className="about-section card">
        <h2>What We Offer</h2>
        <ul className="offer-list">
          <li>🌿 Indoor & Outdoor Plants</li>
          <li>🪴 Designer Pots & Grow Bags</li>
          <li>🌾 Quality Seeds & Bulbs</li>
          <li>🧪 Eco-friendly Pesticides</li>
          <li>🪵 Red Soil, Cocopeat & Potting Mix</li>
          <li>🌻 Gardening Tools & Accessories</li>
        </ul>
      </section>

      {/* ---------- OUR VISION ----------- */}
      <section className="vision-banner">
        <h2>Our Vision</h2>
        <p>
          To spread greenery, reduce pollution, and make gardening a joyful 
          experience for everyone — beginners or plant lovers.
        </p>
      </section>

      {/* ---------- VALUES ----------- */}
      <section className="about-values">
        <div className="value-box">
          <h3>✨ Quality First</h3>
          <p>We deliver only healthy plants and reliable gardening products.</p>
        </div>

        <div className="value-box">
          <h3>🌍 Sustainability</h3>
          <p>Eco-focused packaging and pollution-free gardening solutions.</p>
        </div>

        <div className="value-box">
          <h3>❤️ Customer Happiness</h3>
          <p>Your satisfaction grows our passion for better service.</p>
        </div>

        
      </section>
      <div className="order-button-wrapper">
  <Link to="/" className="order-button">Go to Home</Link>
</div>

    </div>
  );
}