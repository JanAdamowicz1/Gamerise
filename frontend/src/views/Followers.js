import React from 'react';
import '../styles/Follow.css';
import BottomBar from '../components/BottomBar';
import TopBar from '../components/TopBar';
import AccountWidget from "../components/AccountWidget";
import withAuth from "./withAuth";
import axios from "axios";

class Followers extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            accounts: [],
            user: null
        };
    }

    componentDidMount() {
        this.loadFollowers();
    }

    loadFollowers = () => {
        const token = localStorage.getItem('token');

        axios.get(`http://localhost:8080/api/user/me`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                this.setState({ user: response.data });

                return axios.get(`http://localhost:8080/api/user-observed-account/observing/${response.data.userId}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
            })
            .then(response => {
                this.setState({ accounts: response.data });
            })
            .catch(err => console.log(err));
    }

    removeFollower = (observingUserId) => {
        const token = localStorage.getItem('token');

        axios.delete(`http://localhost:8080/api/user-observed-account/unfollow/${observingUserId}/${this.state.user.userId}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                this.loadFollowers();
            })
            .catch(err => console.log(err));
    }

    render() {
        return (
            <div className="follow-container container">
                <TopBar />
                <div className="follow-list">
                    <h2>MY FOLLOWERS</h2>
                    <hr />
                    {this.state.accounts.map((account, index) =>
                        <AccountWidget key={index} account={account} buttonText="Remove follower" onButtonClick={() => this.removeFollower(account.userId)}/>
                    )}
                </div>
                <BottomBar />
            </div>
        );
    }
}

export default withAuth(Followers);