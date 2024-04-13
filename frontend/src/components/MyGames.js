import React from 'react';
import '../styles/MyGames.css';
import BottomBar from './BottomBar';
import TopBar from './TopBar';
import Game from './Game';

class MyGames extends React.Component {
    render() {
        const gameList = Array(20).fill(<Game />);

        return (
            <div className="mygames-container">
                <TopBar />
                <h2>MY GAMES</h2>
                <hr />
                <div className="shelves-container">
                    <div className="shelf">
                        <div className="shelf-title">CURRENTLY PLAYING</div>
                        <div className="games-container">
                            {gameList}
                        </div>
                    </div>
                    <div className="shelf">
                        <div className="shelf-title">WANT TO PLAY</div>
                        <div className="games-container">
                            {gameList}
                        </div>
                    </div>
                    <div className="shelf">
                        <div className="shelf-title">PLAYED</div>
                        <div className="games-container">
                            {gameList}
                        </div>
                    </div>
                </div>
                <BottomBar />
            </div>
        );
    }
}

export default MyGames;