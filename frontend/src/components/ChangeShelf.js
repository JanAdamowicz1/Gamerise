import React from 'react';
import '../styles/ChangeShelf.css';
import BottomBar from './BottomBar';
import TopBar from './TopBar';
import Button from "./Button";
import {Link} from "react-router-dom";

class ChangeShelf extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div className="changeshelf-container">
                <TopBar />
                <img src="default_game.png" alt="Game" className="game-image" />
                <h2>The Witcher 3: Wild Hunt</h2>
                <hr />
                <h3>SELECT A SHELF</h3>
                <div className="shelves-buttons-container">
                    <div className="shelves-container">
                        <label htmlFor="currentlyPlaying">CURRENTLY PLAYING</label>
                        <label htmlFor="played">PLAYED</label>
                        <label htmlFor="wantToPlay">WANT TO PLAY</label>
                    </div>
                    <div className="buttons-container">
                        <input type="radio" id="currentlyPlaying" name="shelf" value="currentlyPlaying" />
                        <input type="radio" id="played" name="shelf" value="played" />
                        <input type="radio" id="wantToPlay" name="shelf" value="wantToPlay" />
                    </div>
                </div>
                <Link to="/mygames">
                    <Button className="button" type="submit" text="Save" />
                </Link>
                <BottomBar />
            </div>
        );
    }
}
export default ChangeShelf;