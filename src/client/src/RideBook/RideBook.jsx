import "./RideBook.css";
import React from "react";
import Map from "./Map/Map.js";

function template() {
  return (
    <div className="ride-book">
      <h1>RideBook</h1>
        <Map />
        <div className="src-dest">
            <div className="center-text">
              <label> Source
                  <input className="center-text" type="text" name="src" />
              </label>
            </div>
            <div className="center-text">
              <label> Destination
                  <input className="center-text" type="text" name="dest" />
              </label>
            </div>
            <div className="center-text">
              <input type="submit" value="Find Ride" />
            </div>
        </div>
    </div>
  );
};

export default template;
