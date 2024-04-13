import React from 'react';
import '../styles/GameWidget.css';
import { FaThumbsUp } from 'react-icons/fa';
import Button from "./Button";
import {Link} from "react-router-dom";

const GameWidget = ({ friendName, showChangeShelfButton }) => {
    return (
        <div className="game-widget">
            <div className="upper_info">
                <div className="game-status">{friendName} started playing</div>
                <div className="game-date">2022-03-01</div>
            </div>
            <div className="bottom_info">
                <img src="default_game.png" alt="Game" className="game-image" />
                <div className="mid_info">
                    <div className="game-title">The Witcher 3: Wild Hunt</div>
                    <Link to="/changeshelf">
                        {showChangeShelfButton && <Button className="button" text="Change shelf" />}
                    </Link>
                </div>
                <div className="likes">
                    <p>Like</p>
                    <FaThumbsUp />
                    <p>10</p>
                </div>
            </div>
        </div>
    );
};

export default GameWidget;