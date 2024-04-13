import React from 'react';
import '../styles/TopBar.css';

const TopBar = () => {
    return (
        <div className="top-bar">
            <img src="default_profile.png" alt="Profile" className="profile-pic" />
            <div className="hello_container">
                <p>Hello!</p>
                <p>username</p>
            </div>
        </div>
    );
};

export default TopBar;