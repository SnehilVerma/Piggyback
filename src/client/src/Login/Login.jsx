import "./Login.css";
import React, {setError, useState} from "react";
import MediaQuery from 'react-responsive';
import {Button, TextField} from '@mui/material';
import {Link, useNavigate } from "react-router-dom";

const Template = (props) => {
  const [state, setState] = useState({
    userName: '',
    password: '',
  });

  const navigate = useNavigate();
  const handleOnSubmit = (event) => {
    if (event){
         const requestOptions = {
                  method: 'POST',
                  headers: { 'Content-Type': 'application/json', 'Accept': 'application/json','Access-Control-Allow-Origin': '*'},
                  body: JSON.stringify(state)
              };
         fetch('http://localhost:8080/login', requestOptions)
         .then( res => {
                if(res.status >= 400) {
                            throw new Error("Server responded with error!");
                        }
                console.log("Log in successful")
                navigate('/RideBook', {state:state})
            }
         )
         .then(data => setState({userName: data.id}))
         .catch(function(error) {
//                 setError('Invalid Username or Password');
            console.error('Invalid Username or Password');
            });
    }
  };

  const handleInputChange = (event) =>{
    const { name, value } = event.target;
    setState((prevState) => ({...prevState, [name]: value}))
  };
  return (
    <div className="login center-text">
        <h1>Login</h1>
        <div>
                <TextField id="outlined-basic" name="userName" variant="outlined" placeholder="Username" label="Username"
                onChange={handleInputChange}/>
        </div>
        <div className="text-field">
                <TextField id="outlined-basic" name="password" variant="outlined" type="password" placeholder="Password"
                label="Password" onChange={handleInputChange}/>
        </div>
        <div className="login-button">
              <Button variant="contained" onClick={handleOnSubmit}>Login</Button>
        </div>
    </div>
  )
};
export default Template;