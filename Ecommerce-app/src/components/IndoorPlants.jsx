import React, { useEffect, useState } from "react";
import "./IndoorPlants.css";
import { Link } from "react-router-dom";


const ITEMS_PER_PAGE = 12;

const IndoorPlants = () => {
  const [plants, setPlants] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [selectedPlant, setSelectedPlant] = useState(null);

  // ---------------- FETCH DATA ----------------
  useEffect(() => {
  fetch(
  "https://ecommerce-application-production-f58b.up.railway.app/users/indoarplants"
)
      .then((res) => res.json())
      .then((data) =>
        setPlants(
          data.map((p) => ({
            ...p,
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
      p.id === plantId ? { ...p, qty: newQty } : p
    )
  );

  // update sidebar also
  setSelectedPlant((prev) =>
    prev && prev.id === plantId ? { ...prev, qty: newQty } : prev
  );

 fetch(
  "https://ecommerce-application-production-f58b.up.railway.app/users/indoarplants/updateQty",
  {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ plant_id: plantId, qty: newQty }),
  });
};


  const increment = (plantId, currentQty) => {
    updateQty(plantId, currentQty + 1);
  };

  const decrement = (plantId, currentQty) => {
    if (currentQty > 0) {
      updateQty(plantId, currentQty - 1);
    }
  };

  // ------------------ Pagination ------------------
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
      <div className="banner-indoar"></div>

      <div className="plants-grid">
        {currentPlants.map((item) => (
          <div key={item.id} className="plant-card">
            <div className="discount-badge">-{item.discount_percent}%</div>

            <img src={item.img_url} className="plant-img" alt={item.common_name} />

            <p className="plant-title">{item.common_name}</p>

            <div className="price-box">
              <span className="old-price">Rs. {item.price}</span>
              <span className="new-price">Rs. {item.selling_price}</span>
            </div>

            <div className="rating-stars">
              {"★".repeat(Math.floor(item.rating))}
            </div>

            <div className="button-row">
              {/* ----------------- VIEW DETAILS ----------------- */}
              <button className="btn-icon view-btn" onClick={() => setSelectedPlant(item)}>
                <i className="bi bi-info-circle"></i>
              </button>

              {/* ----------------- CART BTN + QTY LOGIC ----------------- */}
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

        <button onClick={() => goToPage(currentPage + 1)} disabled={currentPage === totalPages}>
          {">"}
        </button>
      </div>

      {/* ------------- SIDEBAR DETAILS ------------- */}
      {selectedPlant && (
        <div className="details-overlay" onClick={() => setSelectedPlant(null)}>
          <div className="details-panel" onClick={(e) => e.stopPropagation()}>
            <button className="close-btn" onClick={() => setSelectedPlant(null)}>✕</button>

            <h2 className="detail-title">{selectedPlant.common_name}</h2>

            <h3>Description</h3>
            <p className="desc">{selectedPlant.description}</p>

            <h3>Benefits</h3>
            <ul className="list-box">
              {selectedPlant.benefits.map((b, i) => (
                <li key={i}>{b}</li>
              ))}
            </ul>

            <h3>Steps to Grow</h3>
            <ul className="list-box">
              {selectedPlant.steps_to_grow.map((step, i) => (
                <li key={i}>{step}</li>
              ))}
            </ul>

            <div className="details-price">
              <span className="old">Rs. {selectedPlant.price}</span>
              <span className="new">Rs. {selectedPlant.selling_price}</span>
            </div>

            <div className="details-rating">
              {"★".repeat(Math.floor(selectedPlant.rating))}
            </div>

            <div className="details-btn-row">
  {selectedPlant.qty === 0 ? (
    <button
      className="cart-btn-big"
      onClick={() => increment(selectedPlant.id, selectedPlant.qty)}
    >
      <i className="bx bx-cart"></i> Add to Cart
    </button>
  ) : (
    <div className="qty-box-big">
      <button
        className="qty-btn-big"
        onClick={() => decrement(selectedPlant.id, selectedPlant.qty)}
      >
        -
      </button>

      <span className="qty-value-big">{selectedPlant.qty}</span>

      <button
        className="qty-btn-big"
        onClick={() => increment(selectedPlant.id, selectedPlant.qty)}
      >
        +
      </button>
    </div>
  )}

 <Link to={`/buynow/${selectedPlant.id}`} className="buy-now-big link-button">
  Buy Now
</Link>

</div>

          </div>
        </div>
      )}
    </div>
  );
};

export default IndoorPlants;
