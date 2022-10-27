import logo from './logo.svg';
import './App.css';
import React, {Component} from "react";


class App extends Component{
  state = {
    pickupdrop : [],
    pickupdropstring : ''
  };

  async componentDidMount() {
    
    console.log('requesting data');
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ pickup : "umass", drop : "boulders" })
    };
    // const response = await fetch('/queryRoute',requestOptions);
    const response = await fetch('http://localhost:8080/queryRoute',requestOptions);
    const body = await response.text();
    this.setState({pickupdropstring: body})
  }
  

  render() {
    const {pickupdrop} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="App-intro">
              <h2>Testing</h2>
              {
                <div>
                  {this.state.pickupdropstring}
                  </div>
              /* {pickupdrop.map(pd =>
                  <div key={pd.pickup}>
                    {pd.pickup} ({pd.drop})
                  </div>
              )} */}
            </div>
          </header>
        </div>
    );
  }

}

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

export default App;
