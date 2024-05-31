import React from 'react';
import '../styles/FindAccounts.css';
import BottomBar from '../components/BottomBar';
import TopBar from '../components/TopBar';
import AccountWidget from "../components/AccountWidget";
import withAuth from "./withAuth";
import SearchBar from "../components/SearchBar";
import axios from 'axios';

class FindAccounts extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            accounts: [],
            user: null,
            observedAccounts: [],
            searchQuery: "",
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
                this.setState({ user: response.data });
                this.loadObservedUsers();
            })
            .catch(err => console.log(err));
    }

    loadObservedUsers = () => {
        const token = localStorage.getItem('token');

        axios.get(`http://localhost:8080/api/user-observed-account/observed/${this.state.user.userId}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                this.setState({ observedAccounts: response.data });
            })
            .catch(err => console.log(err));
    }

    handleSearch = (accounts, query) => {
        const filteredAccounts = accounts.filter(account =>
            !this.state.observedAccounts.some(observedAccount => observedAccount.userId === account.userId)
        );
        this.setState({ accounts: filteredAccounts, searchQuery: query });
    }

    handleFollow = (accountId) => {
        const token = localStorage.getItem('token');

        axios.post(`http://localhost:8080/api/user-observed-account/follow/${this.state.user.userId}/${accountId}`, {}, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                console.log(response.data);
                this.loadObservedUsers();
                const updatedAccounts = this.state.accounts.filter(account => account.userId !== accountId);
                this.setState({ accounts: updatedAccounts });
                this.handleSearch(updatedAccounts, this.state.searchQuery);
            })
            .catch(err => console.log(err));
    }

    render() {
        return (
            <div className="accounts-container">
                <TopBar />
                <SearchBar onSearch={this.handleSearch} placeholder="Search accounts..." searchType="accounts" nickname={this.state.user?.nickname}/>
                <div className="account-list">
                    {this.state.accounts.map((account, index) =>
                        <AccountWidget key={index} account={account} buttonText="Follow" onButtonClick={() => {
                            this.handleFollow(account.userId);
                        }}/>
                    )}
                </div>
                <BottomBar />
            </div>
        );
    }
}

export default withAuth(FindAccounts);
