import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/GameActivityWidget.css';
import { FaThumbsUp, FaStar } from 'react-icons/fa';

const GameActivityWidget = ({ activity }) => {
    const [imageSrc, setImageSrc] = useState(null);
    const [username, setUsername] = useState('');
    const [shelfName, setShelfName] = useState('');
    const [likes, setLikes] = useState(activity.likes);
    const [liked, setLiked] = useState(false);
    const [userId, setUserId] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('token');

        axios.get(`http://localhost:8080/api/user/me`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                setUserId(response.data.userId);
            })
            .catch(err => console.log(err));
    }, []);

    useEffect(() => {
        const fetchImage = async () => {
            try {
                const token = localStorage.getItem('token');
                const response = await axios.get(`http://localhost:8080/images/${activity.userGame.game.cover}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    },
                    responseType: 'blob'
                });
                const blob = new Blob([response.data], { type: 'image/png' });
                const imageSrc = URL.createObjectURL(blob);
                setImageSrc(imageSrc);
            } catch (err) {
                console.log(err);
            }
        };

        fetchImage();
    }, [activity.userGame.game.cover]);

    useEffect(() => {
        const fetchUsername = async () => {
            try {
                const token = localStorage.getItem('token');
                const response = await axios.get(`http://localhost:8080/api/user/get`, {
                    params: { userId: activity.userGame.user },
                    headers: {
                        'Authorization': `Bearer ${token}`
                    },
                });
                if (response.data) {
                    setUsername(response.data.nickname);
                }
            } catch (err) {
                console.log(err);
            }
        };

        fetchUsername();
    }, [activity.userGame.user]);

    useEffect(() => {
        const fetchShelfName = async () => {
            try {
                const token = localStorage.getItem('token');
                const response = await axios.get(`http://localhost:8080/api/shelf/${activity.activityType}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    },
                });
                if (response.data) {
                    setShelfName(response.data);
                }
            } catch (err) {
                console.log(err);
            }
        };

        fetchShelfName();
    }, [activity.activityType]);

    useEffect(() => {
        const fetchLiked = async () => {
            try {
                const token = localStorage.getItem('token');
                const response = await axios.get(`http://localhost:8080/api/activity/${activity.userActivityId}/liked`, {
                    params: { userId },
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
                setLiked(response.data.liked);
            } catch (err) {
                console.log(err);
            }
        };

        if (userId) {
            fetchLiked();
        }
    }, [activity.userActivityId, userId]);

    const handleLike = async () => {
        try {
            const token = localStorage.getItem('token');

            const response = await axios.post(`http://localhost:8080/api/activity/${activity.userActivityId}/like`, {}, {
                params: { userId },
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            setLikes(response.data.likes);
            setLiked(!liked);
        } catch (err) {
            console.log(err);
        }
    };

    const renderStars = (rating) => {
        const yellowStars = rating;
        const grayStars = 5 - yellowStars;
        const stars = [];

        for (let i = 0; i < yellowStars; i++) {
            stars.push(<FaStar key={i} className="star-yellow" />);
        }

        for (let i = 0; i < grayStars; i++) {
            stars.push(<FaStar key={i + yellowStars} className="star-gray" />);
        }

        return <div className="star-rating">{stars}</div>;
    };

    return (
        <div className="game-activity-widget">
            <div className="upper_info">
                <div className="game-status">{username} {shelfName}</div>
                <div className="stars">
                    {activity.userRating !== null && renderStars(activity.userRating)}
                </div>
                <div className="game-date">{activity.activityDate}</div>
            </div>
            <div className="bottom_info">
                {imageSrc && <img src={imageSrc} alt="Game" className="game-image" />}
                <div className="mid_info">
                    <div className="game-title">{activity.userGame.game.gameName}</div>
                </div>
                <div className={`likes ${liked ? 'liked' : ''}`} onClick={handleLike}>
                    <p>Like</p>
                    <FaThumbsUp className="thumbs-up"/>
                    <p>{likes}</p>
                </div>
            </div>
        </div>
    );
};

export default GameActivityWidget;