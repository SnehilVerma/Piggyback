import "./RideMatch.css";
import { Avatar, Button } from '@mui/material';
import {useNavigate } from "react-router-dom";
import React, {useEffect, useState} from "react";

function Template(){
    const [listening, setListening] = useState(false);
    const [data, setData] = useState([]);
    let eventSource = undefined;
    const navigate = useNavigate();

    useEffect(()=>{
        if(!listening){
              const requestOptions = {
                      method: 'POST',
                      headers: { 'Content-Type': 'application/json' }
                    };
              eventSource = new EventSource("http://localhost:8080/matches/Snehil",{method : 'POST'});

              eventSource.onopen = (event) => {
                console.log("connection opened")
              }

              eventSource.onmessage = (event) => {
                console.log("result",event.data);
                setData(old => [JSON.parse(event.data)])
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
        },[]
     );


    function stringAvatar(name) {
        return {
            sx: {
                bgcolor: stringToColor(name),
            },
//             children: `${name.split(' ')[0][0]}${name.split(' ')[1][0]}`,
        };
    }
    function stringToColor(string) {
        let hash = 0;
        let i;

        for (i = 0; i < string.length; i += 1) {
            hash = string.charCodeAt(i) + ((hash << 5) - hash);
        }

        let color = '#';

        for (i = 0; i < 3; i += 1) {
            const value = (hash >> (i * 8)) & 0xff;
            color += `00${value.toString(16)}`.slice(-2);
        }

        return color;
    }

    const acceptRide = (request) => {
        const accept = window.confirm('Do you want to confirm this ride?');
        if (accept) {
            navigate('/RideConfirm', {state: request})
        }
    }

    const renderRideList = () => {
        if (data && data.length !== 0) {
            return data.map(entry => entry.map(request => (
                <div className="ride-list-result-item" key={request.id}>
                    <div className="ride-list-result-item-individual">
                         <Avatar {...stringAvatar(request.userId)} />
                        <p className="ride-list-driver-rating">
                        <span>{request.rating}</span>
                        <span className="star">&#9733;</span>
                        </p>
                        <p className="ride-list-result-driver"><span>Name: </span>{request.userId  ? request.userId : ''}</p>
                        <p className="ride-list-result-from"><span>From: </span>{request.src  ? request.src : ''}</p>
                        <p className="ride-list-result-to"><span>To: </span>{request.dst ? request.dst  : ''}</p>
                        <Button variant="contained" className="ride-list-accept-btn" onClick={() => acceptRide(request)}>Book</Button>
                    </div>
                </div>
            )))
        } else {
            return (<h3 className="no-rides">You do not have any matches</h3>);
        }
    }
    return (
        <div className="ride-list">
            <div className="ride-list-content">
                <div className="ride-list-title">Ride Options</div>
                {renderRideList()}
            </div>
        </div>
    );
}

export default Template;

