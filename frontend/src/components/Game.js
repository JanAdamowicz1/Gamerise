import React, { useState } from 'react';
import GameWidget from './GameWidget';
import '../styles/Game.css';

const Game = () => {
    const [showWidget, setShowWidget] = useState(false);

    const handleClick = () => {
        setShowWidget(!showWidget);
    };

    const handleOutsideClick = (e) => {
        if (e.target.className === 'overlay') {
            setShowWidget(false);
        }
    };

    return (
        <div onClick={handleClick} className="game-container">
            <img src="default_game.png" alt="Game Cover" className="game-cover" />
            {showWidget &&
                <div className="overlay" onClick={handleOutsideClick}>
                    <GameWidget className="game-widget" friendName="You" showChangeShelfButton={true}/>
                </div>
            }
        </div>
    );
};

export default Game;