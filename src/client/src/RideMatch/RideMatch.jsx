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



const ride1 = new Ride("1", "Human Being", "Destination", "Source");
const ride2 = new Ride("2", "Driver Person", "Destination", "Source");
const ride3 = new Ride("3", "Ride Provider", "Destination", "Source");

const rides = [ride1, ride2, ride3]
// const rides = []


const acceptRide = (request) => {
    const accept = window.confirm('Do you want to confirm this ride?');
    if (accept) {
        console.log(request);
    }
}

const renderRideList = () => {
    if (rides && rides.length !== 0) {
        return rides.map(request => (
            <div className="ride-list-result-item" key={request.id}>
                <div className="ride-list-result-item-individual">
                    <Avatar {...stringAvatar(request.driver)} />
                    <p className="ride-list-result-driver"><span>Name: </span>{request.driver  ? request.driver : ''}</p>
                    <p className="ride-list-result-from"><span>From: </span>{request.from  ? request.from : ''}</p>
                    <p className="ride-list-result-to"><span>To: </span>{request.to ? request.to  : ''}</p>
                    <Button variant="contained" className="ride-list-accept-btn" onClick={() => acceptRide(request)}>Book</Button>
                </div>
            </div>
        ))
    } else {
        return (<h3 className="no-rides">You do not have any matches</h3>);
    }
}
function template() {
    return (
        <div className="ride-list">
            <div className="ride-list-container">
                <div className="ride-list-title">Ride Options</div>
            </div>
            <div className="ride-list-content">
                {renderRideList()}
            </div>
        </div>
    )
};

export default template;

