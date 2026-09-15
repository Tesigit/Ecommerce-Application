import React, { useEffect, useState } from "react";
import "./Soil_Pesticides1.css";
import { Link } from "react-router-dom";

const ITEMS_PER_PAGE = 12;

// Define base URL dynamically (falls back to Railway URL on production)
const API_BASE_URL = import.meta.env.VITE_API_URL || "https://ecommerce-application-production-f58b.up.railway.app";

const Soil_Pesticides1 = () => {
  const [plants, setPlants] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [selectedPlant, setSelectedPlant] = useState(null);

  // ---------------- FETCH DATA WITH SAFE PARSING ----------------
  useEffect(() => {
    fetch(`${API_BASE_URL}/users/soil_pesticides`)
      .then(async (res) => {
        const text = await res.text();
        try {
          return JSON.parse(text);
        } catch (e) {
          console.error("Corrupted JSON character near position 412919:", text.slice(412800, 413000));
          throw e;
        }
      })
      .then((data) =>
        setPlants(
          (data || []).map((p) => ({
            ...p,
            id: p.id || p.plant_id, // Safe fallback for plant ID
            qty: p.qty || 0, // Ensure qty exists
          }))
        )
      )
      .catch((err) => console.error("Error loading data:", err));
  }, []);

  // ------------ UPDATE QTY IN BACKEND + FRONTEND ------------
  const updateQty = (plantId, newQty) => {
    setPlants((prev) =>
      prev.map((p) =>
        (p.id || p.plant_id) === plantId ? { ...p, qty: newQty } : p
      )
    );

    // update sidebar also
    setSelectedPlant((prev) =>
      prev && (prev.id || prev.plant_id) === plantId ? { ...prev, qty: newQty } : prev
    );

    fetch(`${API_BASE_URL}/users/soil_pesticides/updateQty`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ plant_id: plantId, qty: newQty }),
    }).catch((err) => console.error("Error updating quantity:", err));
  };

  const increment = (plantId, currentQty) => {
    updateQty(plantId, currentQty + 1);
  };

  const decrement = (plantId, currentQty) => {
    if (currentQty > 0) {
      updateQty(plantId, currentQty - 1);
    }
  };

  const totalPages = Math.ceil(plants.length / ITEMS_PER_PAGE);
  const startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
  const currentPlants = plants.slice(startIndex, startIndex + ITEMS_PER_PAGE);

  const goToPage = (page) => {
    if (page >= 1 && page <= totalPages) {
      setCurrentPage(page);
      window.scrollTo({ top: 0, behavior: "smooth" });
    }
  };

  return (
    <div className="indoor-container">
      <div className="banner-soil"></div>

      <div className="plants-grid">
        {currentPlants.map((item, index) => {
          const itemId = item.id || item.plant_id;
          return (
            <div key={itemId || index} className="plant-card">
              <div className="discount-badge">-{item.discount_percent || 0}%</div>

              {/* HTTPS secured image URL */}
              <img 
                src={item.img_url ? item.img_url.replace("http://", "https://") : ""} 
                className="plant-img" 
                alt={item.common_name || "Soil & Pesticide"} 
              />

              <p className="plant-title">{item.common_name}</p>

              <div className="price-box">
                <span className="old-price">Rs. {item.price}</span>
                <span className="new-price">Rs. {item.selling_price || item.price}</span>
              </div>

              <div className="rating-stars">
                {"★".repeat(Math.floor(item.rating || 0))}
              </div>

              <div className="button-row">
                {/* VIEW DETAILS */}
                <button className="btn-icon view-btn" onClick={() => setSelectedPlant(item)}>
                  <i className="bi bi-info-circle"></i>
                </button>

                {/* CART BTN + QTY LOGIC */}
                {item.qty === 0 ? (
                  <button
                    className="btn-icon cart-btn"
                    onClick={() => increment(itemId, item.qty)}
                  >
                    <i className="bx bx-cart"></i>
                  </button>
                ) : (
                  <div className="qty-box">
                    <button className="qty-btn" onClick={() => decrement(itemId, item.qty)}>-</button>
                    <span className="qty-value">{item.qty}</span>
                    <button className="qty-btn" onClick={() => increment(itemId, item.qty)}>+</button>
                  </div>
                )}

                <Link to={`/buynow/${itemId}`} className="btn-icon buy-btn">
                  Buy Now
                </Link>
              </div>
            </div>
          );
        })}
      </div>

      {/* Pagination */}
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
          disabled={currentPage === totalPages || totalPages === 0}
        >
          {">"}
        </button>
      </div>

      {/* Half Canvas Sidebar */}
      {selectedPlant && (
        <div className="details-overlay" onClick={() => setSelectedPlant(null)}>
          <div className="details-panel" onClick={(e) => e.stopPropagation()}>
            <button className="close-btn" onClick={() => setSelectedPlant(null)}>
              ✕
            </button>

            <h2 className="detail-title">{selectedPlant.common_name}</h2>
            {selectedPlant.scientific_name && (
              <p className="detail-sci"><i>{selectedPlant.scientific_name}</i></p>
            )}

            <h3>Description</h3>
            <p className="desc">{selectedPlant.description}</p>

            {selectedPlant.quantity && (
              <p className="detail-sci"><i>{selectedPlant.quantity}</i></p>
            )}

            {/* SAFE ARRAY RENDER: BENEFITS */}
            {selectedPlant?.benefits && selectedPlant.benefits.length > 0 && (
              <>
                <h3>Benefits</h3>
                <ul className="list-box">
                  {selectedPlant.benefits.map((b, i) => (
                    <li key={i}>{b}</li>
                  ))}
                </ul>
              </>
            )}

            {/* SAFE ARRAY RENDER: DIRECTIONS TO USE / STEPS TO GROW */}
            {(selectedPlant?.directions_to_use || selectedPlant?.steps_to_grow) && (
              <>
                <h3>Directions to Use</h3>
                <ul className="list-box">
                  {(selectedPlant.directions_to_use || selectedPlant.steps_to_grow).map((step, i) => (
                    <li key={i}>{step}</li>
                  ))}
                </ul>
              </>
            )}

            <div className="details-price">
              <span className="old">Rs. {selectedPlant.price}</span>
              <span className="new">Rs. {selectedPlant.selling_price || selectedPlant.price}</span>
            </div>

            <div className="details-rating">
              {"★".repeat(Math.floor(selectedPlant.rating || 0))}
            </div>

            {/* BUTTON ALIGNMENT */}
            <div className="details-btn-row">
              {selectedPlant.qty === 0 ? (
                <button
                  className="cart-btn-big"
                  onClick={() => increment(selectedPlant.id || selectedPlant.plant_id, selectedPlant.qty)}
                >
                  <i className="bx bx-cart"></i> Add to Cart
                </button>
              ) : (
                <div className="qty-box-big">
                  <button
                    className="qty-btn-big"
                    onClick={() => decrement(selectedPlant.id || selectedPlant.plant_id, selectedPlant.qty)}
                  >
                    -
                  </button>

                  <span className="qty-value-big">{selectedPlant.qty}</span>

                  <button
                    className="qty-btn-big"
                    onClick={() => increment(selectedPlant.id || selectedPlant.plant_id, selectedPlant.qty)}
                  >
                    +
                  </button>
                </div>
              )}

              <Link to={`/buynow/${selectedPlant.id || selectedPlant.plant_id}`} className="buy-now-big link-button">
                Buy Now
              </Link>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Soil_Pesticides1;
