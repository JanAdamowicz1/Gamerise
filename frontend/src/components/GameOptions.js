import React, { useState, useEffect } from 'react';
import Button from "./Button";
import '../styles/GameOptions.css';
import {Link} from "react-router-dom";
import axios from 'axios';

const GameOptions = ({ game, refreshGames }) => {
    const [currentUser, setCurrentUser] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('token');

        axios.get(`http://localhost:8080/api/user/me`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                setCurrentUser(response.data);
            })
            .catch(err => console.log(err));
    }, []);

    const handleDelete = () => {
        const token = localStorage.getItem('token');
        if (currentUser) {
            axios.delete(`http://localhost:8080/api/usergame/delete/user/${currentUser.userId}/game/${game.gameId}`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
                .then(response => {
                    console.log(response.data);
                    refreshGames();
                })
                .catch(err => console.log(err));
        }
    };

    return (
        <div className="game-options">
            <Link to={`/chooseshelf/${game.gameId}`}>
                <Button className="button" text="Change shelf" />
            </Link>
            <Button className="button" text="Delete" onClick={handleDelete} />
        </div>
    );
};

export default GameOptions;