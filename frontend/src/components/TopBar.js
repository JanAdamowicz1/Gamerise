import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/TopBar.css';

const TopBar = () => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('token');

        axios.get(`http://localhost:8080/api/user/me`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                setUser(response.data);
            })
            .catch(err => console.log(err));
    }, []);

    return (
        <div className="top-bar">
            <img src="default_profile.png" alt="Profile" className="profile-pic" />
            <div className="hello_container">
                <p>Hello!</p>
                {user && <p>{user.firstName} {user.lastName}</p>}
            </div>
        </div>
    );
};

export default TopBar;