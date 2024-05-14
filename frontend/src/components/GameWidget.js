import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/GameWidget.css';
import Button from "./Button";
import {Link} from "react-router-dom";

const GameWidget = ({ game }) => {
    const [imageSrc, setImageSrc] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('token');
        axios.get(`http://localhost:8080/images/${game.cover}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            },
            responseType: 'blob'
        })
            .then(response => {
                const blob = new Blob([response.data], {type: 'image/png'});
                const imageSrc = URL.createObjectURL(blob);
                setImageSrc(imageSrc);
            })
            .catch(err => console.log(err));
    }, [game.cover]);

    return (
        <div className="game-widget">
            {imageSrc && <img src={imageSrc} alt="Game" className="game-image" />}
            <div className="game-title">{game.gameName}</div>
            <Link to={`/chooseshelf/${game.gameId}`}>
                <Button className="button" text="Add" />
            </Link>
        </div>
    );
};

export default GameWidget;