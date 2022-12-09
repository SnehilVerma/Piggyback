import "./RideConfirm.css";
import React from "react";
import { Avatar, Button } from '@mui/material';
import {Link } from "react-router-dom";
import Rating from '@mui/material/Rating';
import Stack from '@mui/material/Stack';



function RideDetails(id, driver, pickup, dropoff, phone) {
    this.id = id;
    this.driver = driver;
    this.pickup = pickup;
    this.dropoff = dropoff;
    this.phone = phone;
}

const ride = new RideDetails("1", "Human Being", "142 Brittany Mnr Dr", "337 Russel St", "(123)456-7890", "ride@example.com");

function stringAvatar(name) {
    return {
        sx: {
            bgcolor: stringToColor(name),
        },
        children: `${name.split(' ')[0][0]}${name.split(' ')[1][0]}`,
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


// const updateRide = (ride) => {
//         console.log(ride);
//     }


    const finishRide = (ride) => {
        const finish = window.confirm('Do you want to finish this ride?');
        if (finish) {
            window.location = "/RideBook";
            //Does not pass state
        }
    }

function rating() {
    return (
        <Stack spacing={1}>
            <Rating name="half-rating" defaultValue={0} precision={0.5}/>
        </Stack>
    )
}

function template() {
    return (<div className="ride">
            <div className="ride-title">Ride Details</div>
            <div className="ride-container">
                <Avatar {...stringAvatar(ride.driver)} />
                <p className="ride-user-info">{ride.driver} - {ride.phone}</p>
            <div className="ride-details">
                <p className="ride-from"><span>From: </span>{ride.pickup ? ride.pickup : ''}</p>
                <p className="ride-to"><span>To: </span>{ride.dropoff ? ride.dropoff : ''}</p>
                <Button variant="contained" className="ride-finish" onClick={finishRide}>Finish Ride</Button>
            </div>
        </div>
        </div>
    )
};

export default template;
