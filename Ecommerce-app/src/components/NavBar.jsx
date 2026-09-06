import React, { useEffect, useState } from "react";
import "./NavBar.css";
import FloraHarborWord from "../assets/FloraHarborWord.png";
import Earth_Logo from "../assets/Earth_Logo.png";
import { Link } from 'react-router-dom';
import { useNavigate } from "react-router-dom";

// Main categories with subcategories
const categories = [
  {
    name: "Indoor Plants",
    sub: ["Ferns", "Palms", "Money Plants", "Lucky Bamboo", "Bonsai", "Air Purifying Plants"],
  },
  {
    name: "Outdoor Plants",
    sub: ["Shrubs", "Trees", "Climbers & Vines", "Ornamental Grasses", "Hedge Plants"],
  },
  {
    name: "Flowering Plants",
    sub: ["Roses", "Hibiscus", "Jasmine", "Marigold", "Orchids", "Bougainvillea"],
  },
  {
    name: "Fruits & Vegetables",
    sub: ["Tomato, Chili, Brinjal", "Strawberry, Papaya", "Lemon, Orange", "Berry Plants"],
  },
  {
    name: "Herbs",
    sub: ["Basil (Tulsi)", "Mint", "Rosemary", "Thyme", "Oregano"],
  },
  {
    name: "Succulents & Cacti",
    sub: ["Aloe Vera", "Echeveria", "Cactus varieties", "Jade Plant"],
  },
  {
    name: "Seeds",
    sub: ["Flower seeds (Marigold, Sunflower)", "Vegetable seeds (Tomato, Carrot)"],
  },
  {
    name: "Bulbs",
    sub: ["Bulbs (Tulips, Daffodils)"],
  },
];

function NavBar({ setActiveComponent, setSearchTerm ,user}) {

  
   const [search, setSearch] = useState("");
   const [dropdownOpen, setDropdownOpen] = useState(false);
    const [currentUser, setCurrentUser] = useState(null); 
  const navigate = useNavigate();

  
  // Load user from localStorage on mount
  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem("user"));
    setCurrentUser(storedUser);
  }, []);

   const onSearch = (e) => {
  e.preventDefault();
  const query = search.trim();
  if (!query) return;

  setSearchTerm(query);  
  setActiveComponent("search");  // <<< IMPORTANT
};
  const handleLogout = () => {
    localStorage.removeItem("user");
    setCurrentUser(null); // update state immediately
    setDropdownOpen(false);
    navigate("/"); // optional redirect
  };

  return (
    <>
      {/* Main Navbar */}
<nav className="navbar">
        <div className="navbar__logo">
          <img src={Earth_Logo} alt="Logo" className="logo-img" />
          <img src={FloraHarborWord} alt="Brand" className="logo-img" />
        </div>

        
          {/* CENTER SEARCH BAR (EXPANDED) */}
        <form className="navbar__search expanded" onSubmit={onSearch}>
          <input
            type="text"
            placeholder="Search plants..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
          <button type="submit" >Search</button>
        </form>
          {/* RIGHT BUTTONS — IN YOUR REQUIRED ORDER */}
                 <div className="navbar__right">
   {currentUser  ? (
  <div className="user-avatar-container">
    <div
      className="user-avatar"
      onClick={() => setDropdownOpen(!dropdownOpen)}
    >
      <img
        src={`https://api.dicebear.com/9.x/lorelei/svg?seed=${encodeURIComponent(user.name || user.email)}`}
        alt={user.name || user.email}
        className="avatar-img"
      />
      <span className="user-email">{user.name || user.email}</span>
    </div>

    {/* Dropdown like subnav */}
    {dropdownOpen && (
      <div className="avatar-subnav-dropdown">
        <span onClick={() => navigate("/profile")}>Profile</span>
        <span onClick={handleLogout}>Logout</span>
      </div>
    )}
  </div>
) : (
  <>
    <Link to="/login"><button className="nav-btn">Login</button></Link>
    <Link to="/signup"><button className="nav-btn signup">Sign Up</button></Link>
  </>
)}


  <Link to="/about"><button className="nav-btn">About</button></Link>
  <Link to="/contact"><button className="nav-btn">Contact</button></Link>
  <Link to="/cart"><button className="nav-btn cart-btn">🛒</button></Link>
</div>

      </nav>

      <div className="subnav">
        {categories.map((cat) => (
          <div key={cat.name} className="subnav__item">
            <span
              className="category-name"
              onClick={() => setActiveComponent(cat.name)}
              style={{ cursor: "pointer" }}
            >
              {cat.name}
            </span>
            <div className="subnav__dropdown">
              {cat.sub.map((subItem) => (
                <span
                  key={subItem}
                  className="subnav__subitem"
                  onClick={() => setActiveComponent(cat.name)} 
                  style={{ cursor: "pointer" }}
                >
                  {subItem}
                </span>
              ))}
            </div>
          </div>
        ))}
      </div>
    </>
  );
}

export default NavBar;
