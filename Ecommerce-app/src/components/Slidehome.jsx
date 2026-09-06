import React from "react";
import ScrolingNav1 from "../assets/Scroling-Nav-img1.webp";
import ScrolingNav2 from "../assets/Scroling-Nav-img2.webp";
import ScrolingNav3 from "../assets/Scroling-Nav-img3.webp";
import ScrolingNav4 from "../assets/Scroling-Nav-img4.webp";
import "./Slidehome.css";

const Slidehome = () => {
  return (
    <div id="carouselExampleDark" className="carousel carousel-dark slide" data-bs-ride="carousel">
      
      {/* Carousel Indicators */}
      <div className="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="3" aria-label="Slide 4"></button>
      </div>

      {/* Carousel Slides */}
      <div className="carousel-inner">
        <div className="carousel-item active" data-bs-interval="10000">
          <img src={ScrolingNav1} className="d-block w-100" alt="Slide 1" />
          <div className="carousel-caption d-none d-md-block">
          
          </div>
        </div>

        <div className="carousel-item" data-bs-interval="2000">
          <img src={ScrolingNav2} className="d-block w-100" alt="Slide 2" />
          <div className="carousel-caption d-none d-md-block">
          </div>
        </div>

        <div className="carousel-item" data-bs-interval="2000">
          <img src={ScrolingNav3} className="d-block w-100" alt="Slide 3" />
          <div className="carousel-caption d-none d-md-block">
           
          </div>
        </div>

        <div className="carousel-item" data-bs-interval="2000">
          <img src={ScrolingNav4} className="d-block w-100" alt="Slide 4" />
          <div className="carousel-caption d-none d-md-block">
           
          </div>
        </div>
      </div>

      {/* Carousel Controls */}
      <button
        className="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleDark"
        data-bs-slide="prev"
      >
        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Previous</span>
      </button>

      <button
        className="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleDark"
        data-bs-slide="next"
      >
        <span className="carousel-control-next-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Next</span>
      </button>
    </div>
  );
};

export default Slidehome;
