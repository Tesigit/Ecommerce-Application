import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import "./BuyNowPage.css";

const BuyNowPage = () => {
  const { id } = useParams();
  const [plant, setPlant] = useState(null);
  const [qty, setQty] = useState(1);

useEffect(() => {
  if (!id) return; // stop if id is missing
  fetch(`http://localhost:9000/users/indoarplants/${id}`)
    .then(res => res.json())
    .then(data => setPlant(data))
    .catch(err => console.error("Fetch error:", err));
}, [id]);




  if (!plant) return <h2>Loading...</h2>;

const price = Number(plant.selling_price) || 0;
const originalPrice = Number(plant.price) || 0;
const totalPrice = price * qty;
const discount = originalPrice * qty - totalPrice;
const gst = totalPrice * 0.05;
const grandTotal = totalPrice + gst;


  return (
    <div className="buy-container">
      <div className="buy-card">

        {/* PRODUCT IMAGE */}
        <img src={plant.img_url} alt={plant.common_name} className="buy-img" />

        {/* DETAILS */}
        <div className="buy-details">
          <h2>{plant.common_name}</h2>
          <p className="buy-price">Selling Price: ₹{plant.selling_price}</p>
           <p className="buy-price">Original Price: ₹{plant.price}</p>


          {/* QTY BOX */}
          <div className="qty-box-buy">
            <button onClick={() => setQty(qty > 1 ? qty - 1 : 1)}>-</button>
            <span>{qty}</span>
            <button onClick={() => setQty(qty + 1)}>+</button>
          </div>

          {/* PRICE BREAKDOWN */}
          <div className="price-breakdown">
            <p>Item Price: ₹{price}</p>
            <p>Quantity: {qty}</p>

            <p>Price: ₹{totalPrice}</p>
        

            <p>Discount Saved: <span className="disc">₹{discount}</span></p>
            <p>GST (5%): ₹{gst.toFixed(2)}</p>
            <h2 className="grand-total">Total: ₹{grandTotal.toFixed(2)}</h2>
          </div>

          <Link to="/sucess" className="checkout-btn" >Proceed to Payment</Link>
        </div>
      </div>
    </div>
  );
};

export default BuyNowPage;