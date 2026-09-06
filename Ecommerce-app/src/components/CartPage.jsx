import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./CartPage.css";

const CartPage = () => {
  const [cartItems, setCartItems] = useState([]);
  const navigate = useNavigate();

  // Fetch cart items from backend
  useEffect(() => {
    fetch("http://localhost:9000/users/cart")
      .then((res) => res.json())
      .then(setCartItems)
      .catch((err) => console.error("Failed to fetch cart:", err));
  }, []);

  // Update quantity for an item
  const updateQty = (id, newQty) => {
    if (newQty < 1) return;

    setCartItems((prev) =>
      prev.map((item) => (item.id === id ? { ...item, qty: newQty } : item))
    );

    // Determine which API to call based on category
    const item = cartItems.find((item) => item.id === id);
    if (!item) return;

    let endpoint = "";
    switch (item.category) {
      case "Indoor Plants":
        endpoint = "/users/indoarplants/updateQty";
        break;
      case "Outdoor Plants":
        endpoint = "/users/outdoarplants/updateQty";
        break;
      case "Fruits & Vegetables":
        endpoint = "/users/fruits_vegitables/updateQty";
        break;
      case "Seeds":
        endpoint = "/users/seeds/updateQty";
        break;
      case "Soil & Pesticides":
        endpoint = "/users/soil_pesticides/updateQty";
        break;
      case "Flowering Plants":
        endpoint = "/users/flowering_plants/updateQty";
        break;
      case "Herbs":
        endpoint = "/users/herbs/updateQty";
        break;
      case "Bulbs":
        endpoint = "/users/bulbs/updateQty";
        break;
      case "Succulents & Cactus":
        endpoint = "/users/succulents_cactus/updateQty";
        break;
      case "Accessories":
        endpoint = "/users/plant_accessories/updateQty";
        break;
      default:
        return;
    }

    fetch(`http://localhost:9000${endpoint}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ plant_id: id, qty: newQty }),
    }).catch((err) => console.error("Failed to update qty:", err));
  };

  const increment = (id, qty) => updateQty(id, qty + 1);
  const decrement = (id, qty) => updateQty(id, qty - 1);

  const removeItem = (id) => {
    setCartItems((prev) => prev.filter((item) => item.id !== id));
  };

  // Billing calculations
  const subtotal = cartItems.reduce(
    (sum, item) => sum + item.selling_price * item.qty,
    0
  );
  const gst = subtotal * 0.05;
  const grandTotal = subtotal + gst;

  // Checkout handler
  const handleCheckout = () => {
    fetch("http://localhost:9000/users/cart/clear", { method: "POST" })
      .then(() => navigate("/sucess"))
      .catch((err) => console.error("Checkout failed:", err));
  };

  return (
    <div className="cart-wrapper">
      <div className="cart-header">
        <h2>Your cart</h2>
        <Link to="/" className="continue-shop">
          Continue shopping →
        </Link>
      </div>

      {cartItems.length === 0 ? (
        <div className="empty-cart-box">
          <p>Your cart is empty</p>
          <Link to="/" className="empty-btn">
            🛒 Continue shopping
          </Link>
        </div>
      ) : (
        <>
          {cartItems.map((item) => {
            const price = Number(item.selling_price);
            const original = Number(item.price);

            return (
              <div className="cart-card" key={item.id}>
                <img src={item.image} className="cart-image" alt={item.name} />

                <div className="cart-info">
                  <h3>{item.name}</h3>
                  <p className="price-line">
                    <span className="old-p">₹{original}</span>
                    <span className="new-p">₹{price}</span>
                  </p>
                  <p className="ncash">
                    🔖 Earn {price * 2} ncash (≈₹{(price * 0.2).toFixed(0)})
                  </p>
                </div>

                <div className="right-section">
                  <div className="qty-box-cart">
                    <button onClick={() => decrement(item.id, item.qty)}>-</button>
                    <span>{item.qty}</span>
                    <button onClick={() => increment(item.id, item.qty)}>+</button>
                  </div>

                  <p className="cart-item-price">
                    ₹{(price * item.qty).toFixed(2)}
                  </p>

                  <button className="remove-x" onClick={() => removeItem(item.id)}>
                    ✕
                  </button>
                </div>
              </div>
            );
          })}

          {/* BILLING SUMMARY */}
          <div className="billing-box">
            <h3>Billing Summary</h3>
            <p>Subtotal: ₹{subtotal.toFixed(2)}</p>
            <p>GST (5%): ₹{gst.toFixed(2)}</p>
            <h2 className="grand-total">Grand Total: ₹{grandTotal.toFixed(2)}</h2>
          </div>

          <button className="checkout-btn" onClick={handleCheckout}>
            CHECKOUT
          </button>
        </>
      )}
    </div>
  );
};

export default CartPage;
