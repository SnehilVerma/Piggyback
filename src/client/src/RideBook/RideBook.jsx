import "./RideBook.css";
import React from "react";
import Map from "./Map/Map.js";
import {Button, Autocomplete, TextField, Grid} from '@mui/material';

const srcList = ["a", "b"];
const destList = ["a", "b"];
const params = {};

function submitRide(response){
    console.log(response)
}
function template() {
  return (
            <div className="ride-book">
              <h1 className="title">RideBook</h1>
                <Map className="center-text"/>
                    <div className="src-dest center-text">
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
                        <div className="center-text-button">
                            <Button variant="contained" onClick={submitRide()}>Find Ride</Button>
                        </div>
                    </div>
            </div>
  )
};

export default template;
