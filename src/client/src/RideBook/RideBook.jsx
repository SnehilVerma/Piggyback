import "./RideBook.css";
import React, { useState } from "react";
import Map from "./Map/Map.js";
import {Button, Autocomplete, TextField, Grid} from '@mui/material';
import {Link, useNavigate, useLocation } from "react-router-dom";

const Template = (props) => {
  const location = useLocation();
  const currentUser = location.state.username;
  let [state, setState] = useState({
    userType: '',
    source: '',
    destination: ''
  })
  const srcList = ["142 Brittany Mnr Dr", "171 Brittany Mnr Dr"];
  const destList = ["337 Russel St", "375 Russel St"];
  const params = {};
  const user = ["Driver", "Passenger"];
  let dataToReturn = [];

  const submitRide = (response) => {
      console.log("here")
      const requestOptions = {
          method: 'POST',
          headers: { 'Content-Type': 'application/json', 'Accept': 'application/json','Access-Control-Allow-Origin': '*'},
          body: JSON.stringify(state)
      };
      const sse = new EventSource('http://localhost:8080/requestMatch', requestOptions);
        sse.onmessage = e => console.log(e);
        sse.onerror = (e) => {
          // error log here
          console.log(e.message);
          sse.close();
        }
        return () => {
          sse.close();
        };
  };

  const handleInputChange = (name, value) => {
    setState((prevState) => ({ ...prevState, [name]:value}))
  };
  return (
            <div className="ride-book">
              <h1 className="title">Book a Ride</h1>
                <Map className="center-text-map"/>
                <div className="src-dest center-text">
                        <div className="center-text">
                            <Autocomplete
                                disablePortal
                                name="userType"
                                options={user}
                                sx={{ width: 300, textAlign: "center" , display: "block"}}
                                renderInput={(params) => <TextField {...params} label="Ride Type" />}
                                onChange={(event, value) => handleInputChange('userType', value)}
                            />
                        </div>
                        <div className="center-text">
                                      <Autocomplete
                                        disablePortal
                                        name="source"
                                        options={srcList}
                                        sx={{ width: 300, textAlign: "center" , display: "block"}}
                                        renderInput={(params) => <TextField {...params} label="Source" />}
                                        onChange={(event, value) => handleInputChange('source', value)}
                                      />
                        </div>
                        <div className="center-text">
                                      <Autocomplete
                                        disablePortal
                                        id="destination"
                                        options={destList}
                                        sx={{ width: 300 }}
                                        renderInput={(params) => <TextField {...params} label="Destination" />}
                                        onChange={(event, value) => handleInputChange('destination', value)}
                                      />
                        </div>
                        <div className="center-text-btn">
                            <Link to="/RideMatch">
                                <Button variant="contained" onClick={submitRide}>Find Ride</Button>
                            </Link>
                        </div>
                </div>
            </div>
  )
};

export default Template;
