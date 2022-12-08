import "./Login.css";
import React from "react";
import MediaQuery from 'react-responsive';
import {Button} from '@mui/material';



function submitLogin(){
    console.log("response")
}

const Device = () => (
    <div>
        <MediaQuery minWidth={1224}>
          {/*<p>You are a desktop or laptop</p>*/}
          <MediaQuery minWidth={1824}>
            <p>You also have a huge screen</p>
          </MediaQuery>
        </MediaQuery>
        <MediaQuery minResolution="2dppx">
          {/* You can also use a function (render prop) as a child */}
          {(matches) =>
            matches
              ? <p>
                 {Login()}
              </p>
              : <p>{Login()}</p>
          }
        </MediaQuery>
      </div>
 );

function Login() {
  return (
    <form className="login center-text">
      <h1>Login</h1>
      <div>
          <input className="center-text"
              type="text" value={this} name="username"
          />
      </div>
      <div>
          <input className="center-text"
             type="password" name="password"
          />
      </div>
      <div>
          {/*<input*/}
          {/*  type="submit" value="Submit"*/}
          {/*/>*/}
          <Button variant="contained" onClick={submitLogin()}>Login</Button>
      </div>
    </form>
    )
  };

export default Device;