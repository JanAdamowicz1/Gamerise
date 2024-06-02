import React, {useState} from 'react';
import axios from 'axios';
import '../styles/MyGames.css';
import BottomBar from '../components/BottomBar';
import TopBar from '../components/TopBar';
import Game from '../components/Game';
import withAuth from "./withAuth";


class MyGames extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            currentlyPlayingGames: [],
            wantToPlayGames: [],
            playedGames: [],
            user: null
        };
    }

    componentDidMount() {
        const token = localStorage.getItem('token');

        axios.get(`http://localhost:8080/api/user/me`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                this.setState({user: response.data}, () => {
                    axios.get(`http://localhost:8080/api/usergame/user/${this.state.user.userId}/shelf/1`, {
                        headers: {
                            'Authorization': `Bearer ${token}`
                        }
                    })
                        .then(response => {
                            this.setState({currentlyPlayingGames: response.data});
                        })
                        .catch(err => console.log(err));

                    axios.get(`http://localhost:8080/api/usergame/user/${this.state.user.userId}/shelf/2`, {
                        headers: {
                            'Authorization': `Bearer ${token}`
                        }
                    })
                        .then(response => {
                            this.setState({wantToPlayGames: response.data});
                        })
                        .catch(err => console.log(err));

                    axios.get(`http://localhost:8080/api/usergame/user/${this.state.user.userId}/shelf/3`, {
                        headers: {
                            'Authorization': `Bearer ${token}`
                        }
                    })
                        .then(response => {
                            this.setState({playedGames: response.data});
                        })
                        .catch(err => console.log(err));
                });
            })
            .catch(err => console.log(err));
    }

    refreshGames = () => {
        this.componentDidMount();
    }

    render() {
        return (
            <div className="mygames-container container">
                <TopBar />
                <h2>MY GAMES</h2>
                <hr />
                <div className="shelves-container">
                    <div className="shelf">
                        <div className="shelf-title">CURRENTLY PLAYING</div>
                        <div className="games-container">
                            {this.state.currentlyPlayingGames.map((game, i) =>
                                <Game key={i} game={game} refreshGames={this.refreshGames} />
                            )}
                        </div>
                    </div>
                    <div className="shelf">
                        <div className="shelf-title">WANT TO PLAY</div>
                        <div className="games-container">
                            {this.state.wantToPlayGames.map((game, i) =>
                                <Game key={i} game={game} refreshGames={this.refreshGames} />
                            )}
                        </div>
                    </div>
                    <div className="shelf">
                        <div className="shelf-title">PLAYED</div>
                        <div className="games-container">
                            {this.state.playedGames.map((game, i) =>
                                <Game key={i} game={game} refreshGames={this.refreshGames} />
                            )}
                        </div>
                    </div>
                </div>
                <BottomBar />
            </div>
        );
    }
}

export default withAuth(MyGames);