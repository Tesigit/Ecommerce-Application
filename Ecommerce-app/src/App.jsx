import React, { useState } from "react";
import { Route, Routes } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./App.css";

import Home from "./components/Home";
import SignupPage from "./components/SignupPage";
import LoginPage from "./components/LoginPage";
import About from "./components/About";
import Contact from "./components/Contact";
import IndoorPlants from "./components/IndoorPlants";
import Fruits_Vegitables from "./components/Fruits_Vegitables";
import OutDoarPlants from "./components/OutdoarPlants";
import Seeds_Plants from "./components/Seeds_Plants";
import Soil_Pesticides1 from "./components/Soil_Pesticides1";
import Floweringplants1 from "./components/Floweringplants1";
import HerbsPlants from "./components/HerbsPlants";
import PlantAccessories from "./components/PlantAccessories";
import Bulbs from "./components/BulbsPlants";
import SucculentsCactus from "./components/SucculentsCactus";
import NotFound404 from "./components/NotFound404";
import BuyNowPage from "./components/BuyNowPage";
import OrderSuccess from "./components/OrderSuccess";
import ProfilePage from "./components/ProfilePage";
import CartPage from "./components/CartPage";

function App() {
  const [searchTerm, setSearchTerm] = useState("");

  return (
    <>
      <Routes>
        {/* Main Home Route with dynamic category view & search state */}
        <Route
          path="/"
          element={
            <Home setSearchTerm={setSearchTerm} searchTerm={searchTerm} />
          }
        />

        {/* Authentication Routes */}
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SignupPage />} />

        {/* Informational Pages */}
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />

        {/* Standalone Category Routes */}
        <Route path="/indoor_plants" element={<IndoorPlants />} />
        <Route path="/outdoor_plants" element={<OutDoarPlants />} />
        <Route path="/flowering_plants" element={<Floweringplants1 />} />
        <Route path="/fruits_vegitables" element={<Fruits_Vegitables />} />
        <Route path="/herbs" element={<HerbsPlants />} />
        <Route path="/accessories" element={<PlantAccessories />} />
        <Route path="/seeds" element={<Seeds_Plants />} />
        <Route path="/soil_pesticides" element={<Soil_Pesticides1 />} />
        <Route path="/bulbs" element={<Bulbs />} />
        <Route path="/succulents_cactus" element={<SucculentsCactus />} />

        {/* E-Commerce Checkout & Account Routes */}
        <Route path="/buynow/:id" element={<BuyNowPage />} />
        <Route path="/sucess" element={<OrderSuccess />} />
        <Route path="/success" element={<OrderSuccess />} />
        <Route path="/profile" element={<ProfilePage />} />
        <Route path="/cart" element={<CartPage />} />

        {/* 404 Fallback Route */}
        <Route path="*" element={<NotFound404 />} />
      </Routes>

      {/* Global Toast Notification Container */}
      <ToastContainer position="top-right" autoClose={2000} hideProgressBar={false} />
    </>
  );
}

export default App;