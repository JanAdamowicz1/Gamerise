import React from 'react';
import '../styles/Search.css';
import BottomBar from '../components/BottomBar';
import TopBar from '../components/TopBar';
import GameWidget from '../components/GameWidget';
import SearchBar from "../components/SearchBar";
import withAuth from "./withAuth";

class Search extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            games: []
        };
    }

    handleSearch = (games) => {
        this.setState({ games });
    }

    render() {
        return (
            <div className="search-container">
                <TopBar />
                <SearchBar onSearch={this.handleSearch} />
                <div className="game-widgets">
                    {this.state.games.map((game, i) =>
                        <GameWidget key={i} game={game} showChangeShelfButton={false}/>
                    )}
                </div>
                <BottomBar />
            </div>
        );
    }
}

export default withAuth(Search);