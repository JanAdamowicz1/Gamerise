import React from 'react';
import '../styles/HomePage.css';
import BottomBar from './BottomBar';
import TopBar from './TopBar';
import GameWidget from './GameWidget';
import {Link} from "react-router-dom";
class HomePage extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div className="home-container">
                <TopBar />
                <div className="game-widgets">
                    {Array(20).fill().map((_, i) => <GameWidget key={i} friendName="Friend" showChangeShelfButton={false}/>)}
                </div>
                <BottomBar />
            </div>
        );
    }
}
export default HomePage;