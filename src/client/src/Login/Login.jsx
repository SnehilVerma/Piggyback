import "./Login.css";
import React, {useState} from "react";
import MediaQuery from 'react-responsive';
import {Button, TextField} from '@mui/material';
import {Link, useNavigate } from "react-router-dom";

const Template = (props) => {
  const [state, setState] = useState({
    username: '',
    password: '',
  });

  const navigate = useNavigate();
  const handleOnSubmit = (event) => {
    if (event){
        navigate('/RideBook', {state:state})
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
                <TextField id="outlined-basic" name="username" variant="outlined" placeholder="Username" label="Username"
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