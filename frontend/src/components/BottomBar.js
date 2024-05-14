import React from 'react';
import { FaHome, FaGamepad, FaSearch, FaBars } from 'react-icons/fa';
import '../styles/BottomBar.css';
import {Link} from "react-router-dom";

const BottomBar = () => {
    return (
        <div className="bottom-bar">
            <Link to="/homepage">
                <div className="icon-container">
                    <FaHome />
                    <p>Home</p>
                </div>
            </Link>
            <Link to="/mygames">
                <div className="icon-container">
                    <FaGamepad />
                    <p>My Games</p>
                </div>
            </Link>
            <Link to="/search">
            <div className="icon-container">
                <FaSearch />
                <p>Search</p>
            </div>
            </Link>
            <div className="icon-container">
                <FaBars />
                <p>More</p>
            </div>
        </div>
    );
};

export default BottomBar;