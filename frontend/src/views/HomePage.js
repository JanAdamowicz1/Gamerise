import React from 'react';
import axios from 'axios';
import '../styles/HomePage.css';
import BottomBar from '../components/BottomBar';
import TopBar from '../components/TopBar';
import GameActivityWidget from '../components/GameActivityWidget';
import withAuth from "./withAuth";

class HomePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            userActivities: []
        };
    }

    componentDidMount() {
        const token = localStorage.getItem('token');
        axios.get('http://localhost:8080/api/activity/all', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                console.log(response.data);
                this.setState({userActivities: response.data});
            })
            .catch(err => console.log(err));
    }

    render() {
        return (
            <div className="home-container">
                <TopBar />
                <div className="game-widgets">
                    {this.state.userActivities.map((activity, i) =>
                        <GameActivityWidget key={i} activity={activity} showChangeShelfButton={false}/>
                    )}
                </div>
                <BottomBar />
            </div>
        );
    }
}

export default withAuth(HomePage);