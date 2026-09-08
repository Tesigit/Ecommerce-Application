import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "./IndoorPlants.css"; // reuse styles

const ITEMS_PER_PAGE = 12;

const SearchResults = ({ searchTerm }) => {
  const [results, setResults] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [selectedPlant, setSelectedPlant] = useState(null);

  useEffect(() => {
    if (!searchTerm) {
      setResults([]);
      return;
    }

    axios
  .get(
    `https://ecommerce-application-production-f58b.up.railway.app/users/search?query=${searchTerm}`
  )
  .then((res) =>
    setResults(
      res.data.map((p) => ({ ...p, qty: p.qty || 0 }))
    )
  )
  .catch((err) => console.log(err));
  }, [searchTerm]);

    // Map category names to backend endpoints
  const endpointMap = {
    "Indoor Plants": "indoarplants",
    "Outdoor Plants": "outdoarplants",
    "Fruits & Vegetables": "fruits_vegitables",
    "Seeds": "seeds",
    "Soil & Pesticides": "soil_pesticides",
    "Flowering Plants": "flowering_plants",
    "Herbs": "herbs",
    "Bulbs": "bulbs",
    "Succulents & Cactus": "succulents_cactus",
    "Accessories": "plant_accessories"
  };

  const updateQty = (plantId, newQty, category) => {
    setResults((prev) =>
      prev.map((p) => (p.id === plantId ? { ...p, qty: newQty } : p))
    );

    setSelectedPlant((prev) =>
      prev && prev.id === plantId ? { ...prev, qty: newQty } : prev
    );

   const endpoint = endpointMap[category];
axios.post(
  `https://ecommerce-application-production-f58b.up.railway.app/users/${endpoint}/updateQty`,
  {
    plant_id: plantId,
    qty: newQty
  }
);
};
    

  const increment = (id, qty, category) => updateQty(id, qty + 1, category);
  const decrement = (id, qty, category) => qty > 0 && updateQty(id, qty - 1, category);


  // Pagination
  const totalPages = Math.ceil(results.length / ITEMS_PER_PAGE);
  const startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
  const currentResults = results.slice(startIndex, startIndex + ITEMS_PER_PAGE);

  const goToPage = (page) => {
    if (page >= 1 && page <= totalPages) {
      setCurrentPage(page);
      window.scrollTo({ top: 0, behavior: "smooth" });
    }
  };

  if (!results.length) return <p>No results found.</p>;

  return (
    <div className="indoor-container">
      <div className="plants-grid">
        {currentResults.map((item) => (
          <div key={item.id} className="plant-card">
            {item.discount_percent && <div className="discount-badge">-{item.discount_percent}%</div> }

            <img src={item.img_url || item.image} className="plant-img" alt={item.common_name || item.name} />

           <p
  className="plant-title"
  dangerouslySetInnerHTML={{
    __html: item.name.replace(
      new RegExp(searchTerm, "gi"),
      (match) => `<mark>${match}</mark>`
    ),
  }}
/>

            <div className="category-label">{item.category}</div>


            <div className="price-box">
              <span className="old-price">₹{item.price}</span>
              <span className="new-price">₹{item.selling_price || item.price}</span>
            </div>

            <div className="rating-stars">
              {"★".repeat(Math.floor(item.rating || 0))}
            </div>

            <div className="button-row">
              <button className="btn-icon view-btn" onClick={() => setSelectedPlant(item)}>
                <i className="bi bi-info-circle"></i>
              </button>

              {item.qty === 0 ? (
                <button className="btn-icon cart-btn" onClick={() => increment(item.id, item.qty,item.category)}>
                  <i className="bx bx-cart"></i>
                </button>
              ) : (
                <div className="qty-box">
                  <button className="qty-btn" onClick={() => decrement(item.id, item.qty,item.category)}>-</button>
                  <span className="qty-value">{item.qty}</span>
                  <button className="qty-btn" onClick={() => increment(item.id, item.qty,item.category)}>+</button>
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
        <button onClick={() => goToPage(currentPage - 1)} disabled={currentPage === 1}>{"<"}</button>
        {Array.from({ length: 3 }).map((_, i) => {
          const page = currentPage - 1 + i;
          if (page < 1 || page > totalPages) return null;
          return (
            <button key={page} className={currentPage === page ? "active" : ""} onClick={() => goToPage(page)}>
              {page}
            </button>
          );
        })}
        <button onClick={() => goToPage(currentPage + 1)} disabled={currentPage === totalPages}>{">"}</button>
      </div>

      {/* Sidebar Details */}
      {selectedPlant && (
        <div className="details-overlay" onClick={() => setSelectedPlant(null)}>
          <div className="details-panel" onClick={(e) => e.stopPropagation()}>
            <button className="close-btn" onClick={() => setSelectedPlant(null)}>✕</button>

            <h2 className="detail-title">{selectedPlant.common_name || selectedPlant.name}</h2>

            {selectedPlant.description && (
              <>
                <h3>Description</h3>
                <p className="desc">{selectedPlant.description}</p>
              </>
            )}

            {selectedPlant.benefits && (
              <>
                <h3>Benefits</h3>
                <ul className="list-box">
                  {selectedPlant.benefits.map((b, i) => <li key={i}>{b}</li>)}
                </ul>
              </>
            )}

            {selectedPlant.steps_to_grow && (
              <>
                <h3>Steps to Grow</h3>
                <ul className="list-box">
                  {selectedPlant.steps_to_grow.map((step, i) => <li key={i}>{step}</li>)}
                </ul>
              </>
            )}

            <div className="details-price">
              <span className="old">₹{selectedPlant.price}</span>
              <span className="new">₹{selectedPlant.selling_price || selectedPlant.price}</span>
            </div>

            <div className="details-rating">
              {"★".repeat(Math.floor(selectedPlant.rating || 0))}
            </div>

            <div className="details-btn-row">
              {selectedPlant.qty === 0 ? (
                <button className="cart-btn-big" onClick={() => increment(selectedPlant.id, selectedPlant.qty,selectedPlant.category)}>
                  <i className="bx bx-cart"></i> Add to Cart
                </button>
              ) : (
                <div className="qty-box-big">
                  <button className="qty-btn-big" onClick={() => decrement(selectedPlant.id, selectedPlant.qty,selectedPlant.category)}>-</button>
                  <span className="qty-value-big">{selectedPlant.qty}</span>
                  <button className="qty-btn-big" onClick={() => increment(selectedPlant.id, selectedPlant.qty,selectedPlant.category)}>+</button>
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

export default SearchResults;
