import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import SignIn from './views/SignIn';
import SignUp from './views/SignUp';
import HomePage from './views/HomePage';
import MyGames from "./views/MyGames";
import ChooseShelf from "./views/ChooseShelf";
import Search from "./views/Search";
import Followed from "./views/Followed";
import Followers from "./views/Followers";
import FindAccounts from "./views/FindAccounts";
import MyAccount from "./views/MyAccount";
import AdminPanel from "./views/AdminPanel";
import MyActivities from "./views/MyActivities";

function App() {

    return (
        <Router>
            <Routes>
                <Route path="/" element={<SignIn />} />
                <Route path="/signin" element={<SignIn />} />
                <Route path="/signup" element={<SignUp />} />
                <Route path="/homepage" element={<HomePage />} />
                <Route path="/mygames" element={<MyGames />} />
                <Route path="/chooseshelf/:gameId" element={<ChooseShelf />} />
                <Route path="/search" element={<Search />} />
                <Route path="/followed" element={<Followed />} />
                <Route path="/followers" element={<Followers />} />
                <Route path="/findaccounts" element={<FindAccounts />} />
                <Route path="/myaccount" element={<MyAccount />} />
                <Route path="/admin" element={<AdminPanel />} />
                <Route path="/myactivities" element={<MyActivities />} />
            </Routes>
        </Router>
    );
}

export default App;

