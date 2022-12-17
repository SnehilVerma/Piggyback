import './App.css';
import React from "react";
import UserContext from './Context';
import Login from "./Login/Login";
import RideBook from "./RideBook/RideBook";
import RideMatch from "./RideMatch/RideMatch";
import RideConfirm from "./RideConfirm/RideConfirm";
import HomePage from "./HomePage/HomePage";
import Register from "./Register/Register";
import { BrowserRouter, Routes, Route } from "react-router-dom";


function App() {

  //For testing
  function RideDetails(id, driver, pickup, dropoff, phone) {
    this.id = id;
    this.driver = driver;
    this.pickup = pickup;
    this.dropoff = dropoff;
    this.phone = phone;
  }
  
  const ride = new RideDetails("1", "Human Being", "142 Brittany Mnr Dr", "337 Russel St", "(123)456-7890", "ride@example.com");


return (
    <UserContext.Provider value={ride}>
      <BrowserRouter>
        <Routes>
          <Route exact path="/" element={<HomePage/>} />
          <Route exact path="/register" element={<Register/>} />
          <Route exact path="/login" element={<Login/>} />
          <Route path="/RideBook" element={<RideBook/>} />
          <Route path="/RideMatch" element={<RideMatch/>} />
          <Route path="/RideConfirm" element={<RideConfirm/>} />
        </Routes>
      </BrowserRouter>
    </UserContext.Provider>
);
}

export default App;

