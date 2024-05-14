import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/GameActivityWidget.css';
import { FaThumbsUp } from 'react-icons/fa';

const GameActivityWidget = ({ activity }) => {
    const [imageSrc, setImageSrc] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('token');
        axios.get(`http://localhost:8080/images/${activity.userGame.game.cover}`, {
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
    }, [activity.userGame.game.cover]);

    return (
        <div className="game-activity-widget">
            <div className="upper_info">
                <div className="game-status">{activity.userGame.user.email} {activity.activityType}</div>
                <div className="game-date">{activity.activityDate}</div>
            </div>
            <div className="bottom_info">
                {imageSrc && <img src={imageSrc} alt="Game" className="game-image" />}
                <div className="mid_info">
                    <div className="game-title">{activity.userGame.game.gameName}</div>
                </div>
                <div className="likes">
                    <p>Like</p>
                    <FaThumbsUp />
                    <p>{activity.likes}</p>
                </div>
            </div>
        </div>
    );
};

export default GameActivityWidget;