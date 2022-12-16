import logo from './logo.svg';
import './App.css';
import {useState} from 'react';
import React, {Component, useEffect} from "react";
import UserContext from './Context';
import Login from "./Login/Login";
import RideBook from "./RideBook/RideBook";
import RideMatch from "./RideMatch/RideMatch";
import RideConfirm from "./RideConfirm/RideConfirm";
import HomePage from "./HomePage/HomePage";
import Register from "./Register/Register";
import { BrowserRouter, Routes, Route } from "react-router-dom";
// import { eventSource } from 'react';



// function App() {
//
//   const [listening, setListening] = useState(false);
//   const [data, setData] = useState([]);
//   let eventSource = undefined;
//
//   useEffect(()=>{
//     if(!listening){
//       const requestOptions = {
//               method: 'POST',
//               headers: { 'Content-Type': 'application/json' }
//             };
//       eventSource = new EventSource("http://localhost:8080/requestRide",{method : 'POST'});
//
//       eventSource.onopen = (event) => {
//         console.log("connection opened")
//       }
//
//       eventSource.onmessage = (event) => {
//         console.log("result",event.data);
//         setData(old => [...old,event.data])
//       }
//
//       eventSource.onerror = (event) => {
//         console.log(event.target.readyState)
//         if(event.target.readyState == EventSource.CLOSED){
//           console.log('event source closed ('+event.target.readyState+')')
//         }
//         eventSource.close()
//
//         setListening(true)
//       }
//     }
//
//     return ()=>{
//       eventSource.close();
//       console.log("eventsource closed")
//     }
//   },[])
//
//
//   return (
//     <div className="App">
//       <header className="App-header">
//         <div className="Event data">
//                 Received
//                 {data.map(d =>
//                   <span key={d}>{d}</span>
//                 )}
//             </div>
//       </header>
//     </div>
//   );
// }
// export default App;



//uncomment from here

function App() {

  const currentRide = useState(null);

  
  // useEffect(() => {
  //   initCurrentRide();
  // }, []);
  
  
  //For testing
  function RideDetails(id, driver, pickup, dropoff, phone) {
    this.id = id;
    this.driver = driver;
    this.pickup = pickup;
    this.dropoff = dropoff;
    this.phone = phone;
  }
  
  const ride = new RideDetails("1", "Human Being", "142 Brittany Mnr Dr", "337 Russel St", "(123)456-7890", "ride@example.com");
  
  
  // useEffect(() => {
  //   if (currentRide) {
  
  //   }
  // }, [currentRide]);
  
  // const initCurrentRide = () => {
  //   const currentRide = ride;
  // }



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
      </BrowserRouter>//,
    </UserContext.Provider>
);
}

export default App;

