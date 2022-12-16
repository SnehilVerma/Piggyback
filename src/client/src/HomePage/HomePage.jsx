import "./HomePage.css";
import logo from './Image/mainpage.jpg'; // Tell webpack this JS file uses this image
import React from "react";
import {Button} from '@mui/material';
import {Link} from "react-router-dom";

function template() {
  return (
    <div className="home-page center-text">
      <h1>Home Page</h1>
      <img src={logo} alt="Logo" />
      <div className="login-button">
                    <Link to="/Register" className="register-button">
                        <Button variant="contained">Register</Button>
                    </Link>
                    <Link to="/Login">
                           <Button variant="contained">Login</Button>
                    </Link>
       </div>
    </div>
  );
};

export default template;
