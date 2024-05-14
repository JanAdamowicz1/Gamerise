import React, { useState } from 'react';
import { FaSearch } from 'react-icons/fa';
import axios from 'axios';
import Button from './Button';
import '../styles/SearchBar.css';

const SearchBar = ({ onSearch }) => {
    const [searchTerm, setSearchTerm] = useState('');

    const handleChange = event => {
        setSearchTerm(event.target.value);
    };

    const handleSubmit = async event => {
        event.preventDefault();
        const token = localStorage.getItem('token');
        const response = await axios.get(`http://localhost:8080/api/game/search?query=${searchTerm}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
        console.log(response.data);
        onSearch(response.data);
    };

    return (
        <div className="search-bar">
            <form onSubmit={handleSubmit}>
                <div className="input-wrapper">
                    <FaSearch className="search-icon" />
                    <input
                        type="text"
                        placeholder="Search..."
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