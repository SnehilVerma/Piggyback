import "./RideMatch.css";
import React from "react";
import { Avatar, Button } from '@mui/material';


function Ride(id, driver, to, from) {
    this.id = id;
    this.driver = driver;
    this.to = to;
    this.from = from;
}

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
    /* eslint-enable no-bitwise */

    return color;
}



const ride1 = new Ride("1", "Human Being", "Destination", "Source");
const ride2 = new Ride("2", "Driver Person", "Destination", "Source");
const ride3 = new Ride("3", "Ride Provider", "Destination", "Source");

const rides = [ride1, ride2, ride3]


const acceptRide = (request) => {
    console.log(request);
}

const renderRideList = () => {
    if (rides && rides.length !== 0) {
        return rides.map(request => (
            <div className="ride-list__result-item" key={request.id}>
                <div className="ride-list__result-item_individual">
                    <Avatar {...stringAvatar(request.driver)} />
                    <p className="ride-list__result-label"><span>Name: </span>{request.driver  ? request.driver : ''}</p>
                    <p className="ride-list__result-label"><span>From: </span>{request.from  ? request.from : ''}</p>
                    <p className="ride-list__result-label"><span>To: </span>{request.to ? request.to  : ''}</p>
                    <Button variant="contained" className="ride-list__accept-btn" onClick={() => acceptRide(request)}>Accept</Button>
                </div>
            </div>
        ))
    } else {
        return (<h3 className="empty-message">You do not have any requests</h3>);
    }
}
function template() {
    return (
        <div className="ride-list">
            <div className="ride-list__container">
                <div className="ride-list__title">Ride Options</div>
                <div></div>
            </div>
            <div className="ride-list__content">
                {renderRideList()}
            </div>
        </div>
    )
};

export default template;

