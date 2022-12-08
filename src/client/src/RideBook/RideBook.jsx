import "./RideBook.css";
import React from "react";
import Map from "./Map/Map.js";
import {Button, Autocomplete, TextField, Grid} from '@mui/material';
import {Link } from "react-router-dom";

const srcList = ["142 Brittany Mnr Dr", "171 Brittany Mnr Dr"];
const destList = ["337 Russel St", "375 Russel St"];
const params = {};
const user = ["Driver", "Passenger"];

function submitRide(response){
    console.log(response)
}
function template() {
  return (
            <div className="ride-book">
              <h1 className="title">Book a Ride</h1>
                <Map className="center-text-map"/>
                    <div className="src-dest center-text">
                        <div className="center-text">
                            <Autocomplete
                                disablePortal
                                id="user"
                                options={user}
                                sx={{ width: 300, textAlign: "center" , display: "block"}}
                                renderInput={(params) => <TextField {...params} label="Ride Type" />}
                            />
                        </div>
                        <div className="center-text">
                                      <Autocomplete
                                        disablePortal
                                        id="src"
                                        options={srcList}
                                        sx={{ width: 300, textAlign: "center" , display: "block"}}
                                        renderInput={(params) => <TextField {...params} label="Source" />}
                                      />
                        </div>
                        <div className="center-text">
                                      <Autocomplete
                                        disablePortal
                                        id="dest"
                                        options={destList}
                                        sx={{ width: 300 }}
                                        renderInput={(params) => <TextField {...params} label="Destination" />}
                                      />
                        </div>
                        <div className="center-text-btn">
                            <Link to="/RideMatch">
                            <Button variant="contained">Find Ride</Button>
                                {/*<Button variant="contained" onClick={submitRide()}>Find Ride</Button>*/}
                            </Link>
                        </div>
                    </div>
            </div>
  )
};

export default template;
