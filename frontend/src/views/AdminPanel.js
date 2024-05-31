import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../styles/AdminPanel.css';
import BottomBar from '../components/BottomBar';
import TopBar from '../components/TopBar';
import withAuth from './withAuth';
import Button from "../components/Button";
import StyledInput from "../components/StyledInput";
import { FaGamepad } from "react-icons/fa";

function AdminPanel() {
    const navigate = useNavigate();
    const [gameTitle, setGameTitle] = useState('');
    const [gameCover, setGameCover] = useState(null);

    const handleTitleChange = (event) => {
        setGameTitle(event.target.value);
    };

    const handleCoverChange = (event) => {
        setGameCover(event.target.files[0]);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const formData = new FormData();
        formData.append('title', gameTitle);
        formData.append('cover', gameCover);

        try {
            const response = await axios.post('http://localhost:8080/api/admin/addgame', formData, {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                }
            });
            console.log(response.data);
        } catch (error) {
            console.error('Error adding game', error);
        }
    };

    useEffect(() => {
        async function fetchUserRole() {
            try {
                const response = await axios.get('http://localhost:8080/api/user/role', {
                    headers: {
                        'Authorization': `Bearer ${localStorage.getItem('token')}`
                    }
                });
                console.log('Response data:', response.data);
                if (response.data !== 'ADMIN') {
                    navigate('/homepage');
                }
            } catch (error) {
                console.error('Error fetching user role', error);
            }
        }

        fetchUserRole();
    }, [navigate]);

    return (
        <div className="admin-container">
            <TopBar />
            <h2>ADMIN PANEL</h2>
            <hr />
            <div className="add-game-container">
                <h3>ADD GAME TO DATABASE</h3>
                <hr />
                <form onSubmit={handleSubmit}>
                    <label>
                        <StyledInput type="text" name="email" Icon={FaGamepad} placeholder={"Game title"} onChange={handleTitleChange} />
                    </label>
                    <label>
                        <p>Game cover</p>
                        <input className="fileInput" type="file" onChange={handleCoverChange} />
                    </label>
                    <Button className="button" type="submit" value="Add Game" text="Add"/>
                </form>
            </div>
            <BottomBar />
        </div>
    );
}

export default withAuth(AdminPanel);
