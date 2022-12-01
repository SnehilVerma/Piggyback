import "./Map.css";
import logo from './Image/amherstMap.png'; // Tell webpack this JS file uses this image
import React from 'react';

function Map() {
  return <img src={logo} alt="Logo" />;
}

export default Map;

