import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "./ProfilePage.css"; // import the CSS

const ProfilePage = () => {
  const [userDetails, setUserDetails] = useState(null);

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem("user"));
    if (!storedUser) return;

    axios
      .get(`http://localhost:9000/users/${storedUser.email}`)
      .then(res => setUserDetails(res.data))
      .catch(err => console.error(err));
  }, []);

  if (!userDetails) return <div className="profile-loading">Loading...</div>;

  return (
    <div className="profile-container">
      <div className="profile-card">
        <h2 className="profile-heading">My Profile</h2>
        <img
          src={`https://api.dicebear.com/9.x/lorelei/svg?seed=${encodeURIComponent(
            userDetails.userName || userDetails.email
          )}`}
          alt={userDetails.userName}
          className="profile-avatar"
        />
        <div className="profile-info">
          <p><strong>Name:</strong> {userDetails.userName}</p>
          <p><strong>Email:</strong> {userDetails.email}</p>
          <p><strong>Password:</strong> {userDetails.password}</p>
          <p><strong>Re-enter Password:</strong> {userDetails.reEnterPassword}</p>
        </div>
        <Link to="/" className="order-button">Go to Home</Link>
      </div>
    </div>
  );
};

export default ProfilePage;
