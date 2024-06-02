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
    const [successMessage, setSuccessMessage] = useState('');

    const handleTitleChange = (event) => {
        setGameTitle(event.target.value);
    };

    const handleCoverChange = (event) => {
        setGameCover(event.target.files[0]);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (!gameTitle || !gameCover) {
            alert('Both title and cover must be provided');
            return;
        }

        const formData = new FormData();
        const game = { gameName: gameTitle };

        formData.append('game', new Blob([JSON.stringify(game)], { type: 'application/json' }));
        formData.append('cover', gameCover);

        try {
            const response = await axios.post('http://localhost:8080/api/game/add', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                }
            });
            console.log(response.data);
            setSuccessMessage('Game added successfully');
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
        <div className="admin-container container">
            <TopBar />
            <h2>ADMIN PANEL</h2>
            <hr />
            <div className="add-game-container">
                <h3>ADD GAME TO DATABASE</h3>
                <hr />
                <form onSubmit={handleSubmit}>
                    <label>
                        <StyledInput type="text" name="title" Icon={FaGamepad} placeholder={"Game title"} onChange={handleTitleChange} />
                    </label>
                    <label>
                        <p>Game cover</p>
                        <input className="fileInput" type="file" onChange={handleCoverChange} />
                    </label>
                    <Button className="button" type="submit" value="Add Game" text="Add"/>
                </form>
                {successMessage && <p>{successMessage}</p>}
            </div>
            <BottomBar />
        </div>
    );
}

export default withAuth(AdminPanel);
