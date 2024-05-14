import logo from './logo.svg';
import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import SignIn from './views/SignIn';
import SignUp from './views/SignUp';
import HomePage from './views/HomePage';
import MyGames from "./views/MyGames";
import ChooseShelf from "./views/ChooseShelf";
import Search from "./views/Search";

function App() {
  // return (
  //   <div className="App">
  //     <header className="App-header">
  //       <img src={logo} className="App-logo" alt="logo" />
  //       <p>
  //         Edit <code>src/App.js</code> and save to reload.
  //       </p>
  //       <a
  //         className="App-link"
  //         href="https://reactjs.org"
  //         target="_blank"
  //         rel="noopener noreferrer"
  //       >
  //         Learn React
  //       </a>
  //     </header>
  //   </div>
  // );
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
            </Routes>
        </Router>
    );
}

export default App;

