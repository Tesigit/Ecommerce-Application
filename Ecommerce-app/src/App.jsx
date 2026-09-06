import Home from './components/Home'
import { Route, Routes } from "react-router-dom"
import { useState } from 'react'
import './App.css'

import SignupPage from './components/SignupPage'
import LoginPage from './components/LoginPage'
import About from './components/About'
import Contact from './components/Contact'
import IndoorPlants from './components/IndoorPlants'
import Fruits_Vegitables from './components/Fruits_Vegitables'
import OutDoarPlants from './components/OutdoarPlants'
import Seeds_Plants from './components/Seeds_Plants'
import Soil_Pesticides1 from './components/Soil_Pesticides1'
import Floweringplants1 from './components/Floweringplants1'
import HerbsPlants from './components/HerbsPlants'
import PlantAccessories from './components/PlantAccessories'
import Bulbs from './components/BulbsPlants'
import SucculentsCactus from './components/SucculentsCactus'
import NotFound404 from './components/NotFound404'
import { ToastContainer } from 'react-toastify'
import BuyNowPage from './components/BuyNowPage'
import OrderSuccess from './components/OrderSuccess'
import NavBar from './components/NavBar'
import SearchResults from './components/SearchResults'
import ProfilePage from './components/ProfilePage'
import CartPage from './components/CartPage'

function App() {
  
const [searchTerm, setSearchTerm] = useState(""); 
  return (
    <>
     

      <Routes>
        <Route path="/" element={<Home setSearchTerm={setSearchTerm} searchTerm={searchTerm} />} /> 
       
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SignupPage />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />

        {/* Categories */}
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

        <Route path="/buynow/:id" element={<BuyNowPage />} />
        <Route path="/sucess" element={<OrderSuccess />} />

        <Route path="/profile" element={<ProfilePage />} />
        <Route path="/cart" element={<CartPage></CartPage>} />

        <Route path="*" element={<NotFound404 />} />
      </Routes>

      <ToastContainer />
    </>
  )
}

export default App;
