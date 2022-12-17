import "./RideConfirm.css";
import React from "react";
import { Avatar, Button } from '@mui/material';
import { useLocation } from "react-router-dom";
function stringAvatar(name) {
    return {
        sx: {
            bgcolor: stringToColor(name),
        },
//         children: `${name.split(' ')[0][0]}${name.split(' ')[1][0]}`,
    };
}
function stringToColor(string) {
    let hash = 0;
    let i;

    /* eslint-disable no-bitwise */
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
function Template() {

    const finishRide = () => {
        const finish = window.confirm('Do you want to finish this ride?');
        if (finish) {
            window.location = "/";
            //Does not pass state
        }
    }
    const location = useLocation();
    let ride = location.state;
    ride.phone = "(123) 456-789";

    return (<div className="ride">
            <div className="ride-title">Ride Details</div>
            <div className="ride-container">
                <Avatar {...stringAvatar(ride.userId)} />
                <p className="ride-user-info">{ride.userId} - {ride.phone}</p>
            <div className="ride-details">
                <p className="ride-from"><span>From: </span>{ride.src ? ride.src : ''}</p>
                <p className="ride-to"><span>To: </span>{ride.dst ? ride.dst : ''}</p>
                <Button variant="contained" className="ride-finish" onClick={finishRide}>Finish Ride</Button>
            </div>
        </div>
        </div>
    )
};

export default Template;
