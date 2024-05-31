import React, { useState } from 'react';
import { FaSearch } from 'react-icons/fa';
import axios from 'axios';
import Button from './Button';
import '../styles/SearchBar.css';

const SearchBar = ({ onSearch , placeholder = "Search...", searchType, nickname }) => {
    const [searchTerm, setSearchTerm] = useState('');

    const handleChange = event => {
        setSearchTerm(event.target.value);
    };

    const handleSubmit = async event => {
        event.preventDefault();
        const token = localStorage.getItem('token');
        let url;
        if (searchType === 'games') {
            url = `http://localhost:8080/api/game/search?query=${searchTerm}`;
        } else if (searchType === 'accounts') {
            url = `http://localhost:8080/api/user/search?query=${searchTerm}`;
        }
        const response = await axios.get(url, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
        let data = response.data;
        if (nickname) {
            data = data.filter(account => account.nickname !== nickname);
        }
        console.log(data);
        onSearch(data);
    };

    return (
        <div className="search-bar">
            <form onSubmit={handleSubmit}>
                <div className="input-wrapper">
                    <FaSearch className="search-icon" />
                    <input
                        type="text"
                        placeholder={placeholder}
                        value={searchTerm}
                        onChange={handleChange}
                    />
                    <Button type="submit" text="Search" className="search-button" />
                </div>
            </form>
        </div>
    );
};

export default SearchBar;