import React, { useEffect, useState } from "react";
import "./PlantAccessories.css"; // same CSS reused
import { Link } from "react-router-dom";

const ITEMS_PER_PAGE = 12;

// Define base URL dynamically (falls back to Railway URL on production)
const API_BASE_URL = import.meta.env.VITE_API_URL || "https://ecommerce-application-production-f58b.up.railway.app";

const PlantAccessories = () => {
  const [accessories, setAccessories] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [selectedItem, setSelectedItem] = useState(null);

  // ---------------- FETCH DATA ----------------
  useEffect(() => {
    fetch(`${API_BASE_URL}/users/plant_accessories`)
      .then((res) => res.json())
      .then((data) =>
        setAccessories(
          data.map((p) => ({
            ...p,
            qty: p.qty || 0, // Ensure qty exists
          }))
        )
      )
      .catch((err) => console.error("Error loading data:", err));
  }, []);

  // ------------ UPDATE QTY IN BACKEND + FRONTEND ------------
  const updateQty = (itemId, newQty) => {
    setAccessories((prev) =>
      prev.map((p) =>
        p.id === itemId ? { ...p, qty: newQty } : p
      )
    );

    // update sidebar also
    setSelectedItem((prev) =>
      prev && prev.id === itemId ? { ...prev, qty: newQty } : prev
    );

    fetch(`${API_BASE_URL}/users/plant_accessories/updateQty`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ plant_id: itemId, qty: newQty }),
    });
  };

  const increment = (itemId, currentQty) => {
    updateQty(itemId, currentQty + 1);
  };

  const decrement = (itemId, currentQty) => {
    if (currentQty > 0) {
      updateQty(itemId, currentQty - 1);
    }
  };

  const totalPages = Math.ceil(accessories.length / ITEMS_PER_PAGE);
  const startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
  const currentItems = accessories.slice(startIndex, startIndex + ITEMS_PER_PAGE);

  const goToPage = (page) => {
    if (page >= 1 && page <= totalPages) {
      setCurrentPage(page);
      window.scrollTo({ top: 0, behavior: "smooth" });
    }
  };

  return (
    <div className="indoor-container">
      <div className="banner-accessories"></div>

      <div className="plants-grid">
        {currentItems.map((item, index) => (
          <div key={index} className="plant-card">
            <div className="discount-badge">-{item.discount_percent}%</div>

            {/* HTTPS secured image URL */}
            <img 
              src={item.img_url ? item.img_url.replace("http://", "https://") : ""} 
              className="plant-img" 
              alt={item.name} 
            />

            <p className="plant-title">{item.name}</p>

            <div className="price-box">
              <span className="old-price">Rs. {item.price}</span>
              <span className="new-price">Rs. {item.selling_price}</span>
            </div>

            <div className="rating-stars">
              {"★".repeat(Math.floor(item.rating))}
            </div>

            <div className="button-row">
              {/* VIEW DETAILS */}
              <button className="btn-icon view-btn" onClick={() => setSelectedItem(item)}>
                <i className="bi bi-info-circle"></i>
              </button>

              {/* CART BTN + QTY LOGIC */}
              {item.qty === 0 ? (
                <button
                  className="btn-icon cart-btn"
                  onClick={() => increment(item.id, item.qty)}
                >
                  <i className="bx bx-cart"></i>
                </button>
              ) : (
                <div className="qty-box">
                  <button className="qty-btn" onClick={() => decrement(item.id, item.qty)}>-</button>
                  <span className="qty-value">{item.qty}</span>
                  <button className="qty-btn" onClick={() => increment(item.id, item.qty)}>+</button>
                </div>
              )}

              <Link to={`/buynow/${item.id}`} className="btn-icon buy-btn">
                Buy Now
              </Link>
            </div>
          </div>
        ))}
      </div>

      {/* PAGINATION */}
      <div className="pagination">
        <button onClick={() => goToPage(currentPage - 1)} disabled={currentPage === 1}>
          {"<"}
        </button>

        {Array.from({ length: 3 }).map((_, i) => {
          const page = currentPage - 1 + i;
          if (page < 1 || page > totalPages) return null;

          return (
            <button
              key={page}
              className={currentPage === page ? "active" : ""}
              onClick={() => goToPage(page)}
            >
              {page}
            </button>
          );
        })}

        <button
          onClick={() => goToPage(currentPage + 1)}
          disabled={currentPage === totalPages}
        >
          {">"}
        </button>
      </div>

      {/* DETAILS SIDEBAR */}
      {selectedItem && (
        <div className="details-overlay" onClick={() => setSelectedItem(null)}>
          <div className="details-panel" onClick={(e) => e.stopPropagation()}>
            <button className="close-btn" onClick={() => setSelectedItem(null)}>
              ✕
            </button>

            <h2 className="detail-title">{selectedItem.name}</h2>
            <p className="detail-sci">{selectedItem.category}</p>

            <h3>Description</h3>
            <p className="desc">{selectedItem.description}</p>

            {selectedItem.benefits && (
              <>
                <h3>Benefits</h3>
                <ul className="list-box">
                  {selectedItem.benefits.map((b, i) => (
                    <li key={i}>{b}</li>
                  ))}
                </ul>
              </>
            )}

            {selectedItem.usage_steps && (
              <>
                <h3>Usage Steps</h3>
                <ul className="list-box">
                  {selectedItem.usage_steps.map((s, i) => (
                    <li key={i}>{s}</li>
                  ))}
                </ul>
              </>
            )}

            <div className="details-price">
              <span className="old">Rs. {selectedItem.price}</span>
              <span className="new">Rs. {selectedItem.selling_price}</span>
            </div>

            <div className="details-rating">
              {"★".repeat(Math.floor(selectedItem.rating))}
            </div>

            <div className="details-btn-row">
              {selectedItem.qty === 0 ? (
                <button
                  className="cart-btn-big"
                  onClick={() => increment(selectedItem.id, selectedItem.qty)}
                >
                  <i className="bx bx-cart"></i> Add to Cart
                </button>
              ) : (
                <div className="qty-box-big">
                  <button
                    className="qty-btn-big"
                    onClick={() => decrement(selectedItem.id, selectedItem.qty)}
                  >
                    -
                  </button>

                  <span className="qty-value-big">{selectedItem.qty}</span>

                  <button
                    className="qty-btn-big"
                    onClick={() => increment(selectedItem.id, selectedItem.qty)}
                  >
                    +
                  </button>
                </div>
              )}

              <Link to={`/buynow/${selectedItem.id}`} className="buy-now-big link-button">
                Buy Now
              </Link>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default PlantAccessories;