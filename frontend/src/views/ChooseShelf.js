import React, { useState, useEffect } from 'react';
import '../styles/ChooseShelf.css';
import BottomBar from '../components/BottomBar';
import TopBar from '../components/TopBar';
import Button from "../components/Button";
import {Link, useParams} from "react-router-dom";
import axios from 'axios';
import withAuth from "./withAuth";

const ChooseShelf = () => {
    const [game, setGame] = useState(null);
    const [selectedShelf, setSelectedShelf] = useState(null);
    const [userGames, setUserGames] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [imageSrc, setImageSrc] = useState(null);
    const [currentUser, setCurrentUser] = useState(null);
    const { gameId } = useParams();

    useEffect(() => {
        console.log(`Game ID: ${gameId}`);
        const token = localStorage.getItem('token');

        axios.get(`http://localhost:8080/api/game/get?gameId=${gameId}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                console.log(response.data);
                setGame(response.data);
                return axios.get(`http://localhost:8080/images/${response.data.cover}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    },
                    responseType: 'blob'
                });
            })
            .then(response => {
                const blob = new Blob([response.data], {type: 'image/png'});
                const imageSrc = URL.createObjectURL(blob);
                setImageSrc(imageSrc);
            })
            .catch(err => console.log(err));

        axios.get(`http://localhost:8080/api/user/me`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                setCurrentUser(response.data);
                return axios.get(`http://localhost:8080/api/usergame/get/user/${response.data.userId}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
            })
            .then(response => {
                setUserGames(response.data);
            })
            .catch(err => console.log(err));
    }, [gameId]);

    const handleSave = () => {
        const token = localStorage.getItem('token');

        if (!token) {
            console.log("No token found");
            return;
        }

        if (!selectedShelf) {
            console.log("Please select a shelf");
        }

        setIsLoading(true);

        const userGame = {
            user: {
                userId: currentUser.userId
            },
            game: {
                gameId: game.gameId
            },
            shelf: {
                shelfId: selectedShelf
            }
        };

        const existingUserGame = userGames.find(userGame => userGame.game.gameId === game.gameId);

        if (existingUserGame) {
            userGame.id = existingUserGame.id;
            axios.put(`http://localhost:8080/api/usergame/update/user/${currentUser.userId}/game/${game.gameId}`, userGame, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
                .then(response => {
                    console.log(response.data);
                    setIsLoading(false);
                })
                .catch(err => {
                    console.log(err);
                    setIsLoading(false);
                });
        } else {
            axios.post(`http://localhost:8080/api/usergame/add`, userGame, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
                .then(response => {
                    console.log(response.data);
                    setIsLoading(false);
                })
                .catch(err => {
                    console.log(err);
                    setIsLoading(false);
                });
        }
    };

    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (!game) {
        return <div>Loading...</div>;
    }

    return (
        <div className="changeshelf-container">
            <TopBar />
            {imageSrc && <img src={imageSrc} alt="Game" className="game-image" />}
            <h2>{game.gameName}</h2>
            <hr />
            <h3>SELECT A SHELF</h3>
            <div className="shelves-buttons-container">
                <div className="shelves-container">
                    <label htmlFor="currentlyPlaying">CURRENTLY PLAYING</label>
                    <label htmlFor="wantToPlay">WANT TO PLAY</label>
                    <label htmlFor="played">PLAYED</label>
                </div>
                <div className="buttons-container">
                    <input type="radio" id="currentlyPlaying" name="shelf" value="currentlyPlaying" onClick={() => setSelectedShelf(1)} />
                    <input type="radio" id="wantToPlay" name="shelf" value="wantToPlay" onClick={() => setSelectedShelf(2)} />
                    <input type="radio" id="played" name="shelf" value="played" onClick={() => setSelectedShelf(3)} />
                </div>
            </div>
            <Link to="/mygames">
                <Button className="button" type="submit" text="Save" onClick={handleSave} />
            </Link>
            <BottomBar />
        </div>
    );
}

export default withAuth(ChooseShelf);