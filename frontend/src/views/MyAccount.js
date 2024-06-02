import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/MyAccount.css';
import BottomBar from '../components/BottomBar';
import TopBar from '../components/TopBar';
import withAuth from "./withAuth";
import Button from "../components/Button";

const MyAccount = () => {
    const [user, setUser] = useState(null);
    const [profilePicture, setProfilePicture] = useState(null);
    const [selectedFile, setSelectedFile] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('token');

        axios.get('http://localhost:8080/api/user/me', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                setUser(response.data);
                axios.get(`http://localhost:8080/api/user/profilePicture?userId=${response.data.userId}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                })
                    .then(imageResponse => {
                        const imageUrl = `http://localhost:8080/profile-pictures/${imageResponse.data}`;
                        setProfilePicture(imageUrl);
                    })
                    .catch(error => {
                        console.error("There was an error fetching the profile picture!", error);
                    });
            })
            .catch(err => console.log(err));
    }, []);

    const handleFileChange = (event) => {
        const file = event.target.files[0];
        setSelectedFile(file);
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        const formData = new FormData();
        formData.append('file', selectedFile);

        const token = localStorage.getItem('token');

        axios.post('http://localhost:8080/api/user/uploadProfilePicture', formData, {
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'multipart/form-data'
            }
        })
            .then(response => {
                console.log(response.data);
                setProfilePicture(`http://localhost:8080/profile-pictures/${response.data}`);
            })
            .catch(error => {
                console.error("There was an error uploading the file!", error);
            });
    };

    return (
        <div className="myaccount-container container">
            <TopBar />
            <h3>{user ? user.nickname : 'Loading...'}</h3>
            <hr />
            {profilePicture && <img src={profilePicture} alt="Profile picture" className="profile-picture" />}
            <form onSubmit={handleSubmit}>
                <input type="file" className="fileInput" id="fileInput" onChange={handleFileChange} />
                <Button className="button" type="submit" value="Upload" text="Upload" />
            </form>
            <BottomBar />
        </div>
    );
};

export default withAuth(MyAccount);
