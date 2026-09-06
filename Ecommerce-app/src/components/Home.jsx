import React, { useState } from "react";
import NavBar from "./NavBar";
import Slidehome from "./Slidehome";
import IndoorPlants from "./IndoorPlants";
import Fruits_Vegitables from "./Fruits_Vegitables";
import OutDoarPlants from "./OutdoarPlants";
import Floweringplants1 from "./Floweringplants1";
import HerbsPlants from "./HerbsPlants";
import PlantAccessories from "./PlantAccessories";
import Seeds_Plants from "./Seeds_Plants";
import Soil_Pesticides1 from "./Soil_Pesticides1";
import Bulbs from "./BulbsPlants";
import SucculentsCactus from "./SucculentsCactus";
import About from "./About";
import Contact from "./Contact";
import Footer from "./Footer";
import SearchResults from "./SearchResults";

const Home = ({ setSearchTerm, searchTerm }) => {
  const [activeComponent, setActiveComponent] = useState("");

  // ✅ Get logged-in user from localStorage
  const user = JSON.parse(localStorage.getItem("user"));

  const renderComponent = () => {
    switch (activeComponent) {
      case "search":
        return <SearchResults searchTerm={searchTerm} />;
      case "Indoor Plants":
        return <IndoorPlants />;
      case "Outdoor Plants":
        return <OutDoarPlants />;
      case "Flowering Plants":
        return <Floweringplants1 />;
      case "Fruits & Vegetables":
        return <Fruits_Vegitables />;
      case "Herbs":
        return <HerbsPlants />;
      case "Succulents & Cacti":
        return <SucculentsCactus />;
      case "Seeds":
        return <Seeds_Plants />;
      case "Bulbs":
        return <Bulbs />;
      case "Accessories":
        return <PlantAccessories />;
      case "Soil & Pesticides":
        return <Soil_Pesticides1 />;

      default:
        // First load: show IndoorPlants
        // When searching or category selected: do NOT show IndoorPlants
        return activeComponent ? null : <IndoorPlants />;
    }
  };

  return (
    <div>
     <NavBar 
        setActiveComponent={setActiveComponent}
        setSearchTerm={setSearchTerm}
        user={user}   // ✅ Pass user info to NavBar
      />

      {/* This part changes dynamically */}
      {renderComponent()}

      {/* These always stay on the page */}
      <Slidehome />
      <About />
      <Contact />
      <Footer />
    </div>
  );
};

export default Home;
