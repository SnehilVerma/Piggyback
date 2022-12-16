import "./RideBook.css";
import React, { useState } from "react";
import Map from "./Map/Map.js";
import {Button, Autocomplete, TextField, Grid} from '@mui/material';
import {Link, useNavigate, useLocation } from "react-router-dom";

const Template = (props) => {
  const location = useLocation();
  const currentUser = location.state.userName;
  let [state, setState] = useState({
    userId: currentUser,
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
      const requestOptions = {
          method: 'POST',
          headers: { 'Content-Type': 'application/json', 'Accept': 'application/json','Access-Control-Allow-Origin': '*'},
          body: JSON.stringify({
                                   "userId": state.userId,
                                   "src": state.source,
                                   "dst": state.destination,
                                   "type": state.userType
                               })
      };
      fetch('http://localhost:8080/requestMatch', requestOptions)
                  .then( res => {
                         if(res.status >= 400) {
                                     throw new Error("Server responded with error!");
                                 }
                         console.log("Ride Book request sent")
                     }
                  )
                  .then(data => setState({userName: data.id}))
                  .catch(function(error) {
         //                 setError('Error occurred while requesting ride!);
                     console.error('Error occurred while requesting ride!');
                     });
  };

  const handleInputChange = (name, value) => {
    setState((prevState) => ({ ...prevState, [name]:value}))
  };
  return (
            <div className="ride-book">
              <h1 className="title">Book a Ride</h1>
                <Map className="center-text-map"/>
                <div className="src-dest center-text">
                        <div className="center-text padding-bottom">
                            <Autocomplete
                                disablePortal
                                name="userType"
                                options={user}
                                sx={{ width: 300, textAlign: "center" , display: "block"}}
                                renderInput={(params) => <TextField {...params} label="Ride Type" />}
                                onChange={(event, value) => handleInputChange('userType', value)}
                            />
                        </div>
                        <div className="center-text padding-bottom">
                                      <Autocomplete
                                        disablePortal
                                        name="source"
                                        options={srcList}
                                        sx={{ width: 300, textAlign: "center" , display: "block"}}
                                        renderInput={(params) => <TextField {...params} label="Source" />}
                                        onChange={(event, value) => handleInputChange('source', value)}
                                      />
                        </div>
                        <div className="center-text padding-bottom">
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
