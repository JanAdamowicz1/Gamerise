import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/AccountWidget.css';
import Button from "./Button";

const AccountWidget = ({ account , buttonText="Remove", onButtonClick }) => {
    const [profilePicture, setProfilePicture] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('token');

        axios.get(`http://localhost:8080/api/user/profilePicture?userId=${account.userId}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(imageResponse => {
                const imageUrl = `http://localhost:8080/profile-pictures/${imageResponse.data}`;
                setProfilePicture(imageUrl);
            })
            .catch(error => {
                console.error("There was an error fetching the profile picture!", error);
            });
    }, [account.userId]);

    return (
        <div className="follow-widget">
            <div className="follow-info">
                {profilePicture && <img src={profilePicture} alt="Profile picture" className="follow-image" />}
                <div className="follow-name"><p>{account.nickname}</p></div>
            </div>
            <Button className="button" text={buttonText} onClick={onButtonClick} />
        </div>
    );
};

export default AccountWidget;