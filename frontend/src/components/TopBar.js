import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/TopBar.css';

const TopBar = () => {
    const [user, setUser] = useState(null);
    const [profilePicture, setProfilePicture] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('token');

        axios.get(`http://localhost:8080/api/user/me`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                setUser(response.data);
                axios.get(`http://localhost:8080/api/user/profilePicture?userId=${response.data.userId}`, {
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
            })
            .catch(err => console.log(err));
    }, []);

    return (
        <div className="top-bar">
            <img src={profilePicture} alt="Profile" className="profile-pic" />
            <div className="hello_container">
                <p>Hello!</p>
                {user && <p>{user.nickname} </p>}
            </div>
        </div>
    );
};

export default TopBar;