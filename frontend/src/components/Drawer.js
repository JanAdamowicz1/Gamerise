import React, {useEffect} from 'react';
import {Link, useNavigate} from "react-router-dom";
import axios from 'axios';
import '../styles/Drawer.css';
import Button from "./Button";

const Drawer = ({ isOpen, toggleDrawer }) => {
    const navigate = useNavigate();
    const [role, setRole] = React.useState('');

    useEffect(() => {
        const fetchRole = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/user/role', {
                    headers: {
                        'Authorization': `Bearer ${localStorage.getItem('token')}`
                    }
                });
                setRole(response.data);
            } catch (error) {
                console.error('Error fetching user role', error);
            }
        };

        fetchRole().catch(error => console.error('Error in fetchRole', error));
    }, []);

    const handleLogout = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/auth/logout', {}, {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                }
            });

            if (response.status === 200) {
                localStorage.removeItem('token');
                toggleDrawer();
                navigate('/signin');
            } else {
                console.error('Logout failed');
            }
        } catch (error) {
            console.error('Logout failed', error);
        }
    };

    return (
        <div className={`drawer ${isOpen ? 'open' : ''}`}>
            <Button onClick={toggleDrawer} text="x"></Button>
            <div className="buttons-container">
                <Link to="/followed">
                    <Button  className="button" text="Followed"></Button>
                </Link>
                <Link to="/followers">
                    <Button  className="button" text="Followers"></Button>
                </Link>
                <Link to="/findaccounts">
                    <Button  className="button" text="Find accounts"></Button>
                </Link>
                <Link to="/myaccount">
                    <Button  className="button" text="My account"></Button>
                </Link>
                <Link to="/myactivities">
                    <Button  className="button" text="My activities"></Button>
                </Link>
                {role === 'ADMIN' && (
                    <Link to="/admin">
                        <Button className="button" text="Admin Panel" />
                    </Link>
                )}
            </div>
            <Button onClick={handleLogout} className="button" text="Log out" />
        </div>
    );
};

export default Drawer;