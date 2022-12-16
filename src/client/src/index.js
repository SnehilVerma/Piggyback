import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';

import { useState, useEffect } from 'react';
import Context from './Context';

import { BrowserRouter, Routes, Route, Link } from "react-router-dom";

// import here the component you want to render
import Login from "./Login/Login.js";
import RideBook from "./RideBook/RideBook.js"
import RideMatch from "./RideMatch/RideMatch.js";
import RideConfirm from "./RideConfirm/RideConfirm.js";

const root = ReactDOM.createRoot(document.getElementById('root'));

// root.render(
//     <BrowserRouter>
//         <Routes>
//             <Route exact path="/" element={<Login/>} />
//             <Route path="/RideBook" element={<RideBook/>} />
//             <Route path="/RideMatch" element={<RideMatch/>} />
//             <Route path="/RideConfirm" element={<RideConfirm/>} />
//         </Routes>
//     </BrowserRouter>,
// );


// Add here the name of the component you want to render - For Eg. RideBook
root.render(
    <React.StrictMode>
        <App />
    </React.StrictMode>,
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
