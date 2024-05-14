import React, { useState, useEffect } from 'react';
import axios from 'axios';
import GameOptions from './GameOptions';
import '../styles/Game.css';

const Game = ({ game, refreshGames }) => {
    const [showOptions, setShowOptions] = useState(false);
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

    const handleClick = (event) => {
        event.stopPropagation();
        setShowOptions(!showOptions);
    };

    const handleOutsideClick = () => {
        setShowOptions(false);
    };

    return (
        <div onClick={handleClick} className="game-container">
            {imageSrc && <img src={imageSrc} alt="Game Cover" className="game-cover" />}
            {showOptions &&
                <div className="overlay" onClick={handleOutsideClick}>
                    <GameOptions className="game-options" game={game} refreshGames={refreshGames} onClick={(event) => event.stopPropagation()} />
                </div>
            }
        </div>
    );
};

export default Game;