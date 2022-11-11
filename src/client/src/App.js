import logo from './logo.svg';
import './App.css';
import {useState} from 'react';
import React, {Component, useEffect} from "react";
// import { eventSource } from 'react';


// class App extends Component{
//   state = {
//     pickupdrop : [],
//     pickupdropstring : ''
//   };


//   async componentDidMount() {
    
//     console.log('requesting data');
//     const requestOptions = {
//       method: 'POST',
//       headers: { 'Content-Type': 'application/json' },
//       body: JSON.stringify({ pickup : "umass", drop : "boulders" })
//     };
//     // const response = await fetch('/queryRoute',requestOptions);
//     const response = await fetch('http://localhost:8080/queryRoute',requestOptions);
//     const body = await response.text();
//     this.setState({pickupdropstring: body})
//   }
  

//   render() {
//     const {pickupdrop} = this.state;

//     return (
//         <div className="App">
//           <header className="App-header">
//             <img src={logo} className="App-logo" alt="logo" />
//             <div className="App-intro">
//               <h2>Testing</h2>
//               {
//                 <div>
//                   {this.state.pickupdropstring}
//                   </div>
//               }
//             </div>
//           </header>
//         </div>
//     );
//   }

// }

function App() {

  const [listening, setListening] = useState(false);
  const [data, setData] = useState([]);
  let eventSource = undefined;

  useEffect(()=>{
    if(!listening){
      const requestOptions = {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' }
            };
      eventSource = new EventSource("http://localhost:8080/requestRide",{method : 'POST'});

      eventSource.onopen = (event) => {
        console.log("connection opened")
      }

      eventSource.onmessage = (event) => {
        console.log("result",event.data);
        setData(old => [...old,event.data])
      }

      eventSource.onerror = (event) => {
        console.log(event.target.readyState)
        if(event.target.readyState == EventSource.CLOSED){
          console.log('event source closed ('+event.target.readyState+')')
        }
        eventSource.close()

        setListening(true)
      }
    }

    return ()=>{
      eventSource.close();
      console.log("eventsource closed")
    }
  },[])


  return (
    <div className="App">
      <header className="App-header">
        <div className="Event data">
                Received
                {data.map(d =>
                  <span key={d}>{d}</span>
                )}
            </div>
      </header>
    </div>
  );
}

export default App;
