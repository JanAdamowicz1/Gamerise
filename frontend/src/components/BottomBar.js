import React, { useState } from 'react';
import { FaHome, FaGamepad, FaSearch, FaBars } from 'react-icons/fa';
import { Link } from "react-router-dom";
import Drawer from './Drawer';
import '../styles/BottomBar.css';

const BottomBar = () => {
    const [drawerOpen, setDrawerOpen] = useState(false);

    const toggleDrawer = () => {
        setDrawerOpen(!drawerOpen);
    };

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
                    <p>Search games</p>
                </div>
            </Link>
            <div className="icon-container" onClick={toggleDrawer}>
                <FaBars />
                <p>More</p>
            </div>
            <Drawer isOpen={drawerOpen} toggleDrawer={toggleDrawer} />
        </div>
    );
};

export default BottomBar;