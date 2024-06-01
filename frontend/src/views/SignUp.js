import React from 'react';
import '../styles/SignUp.css';
import { FaEnvelope, FaKey, FaUser } from 'react-icons/fa';
import StyledInput from '../components/StyledInput';
import Button from '../components/Button';
import { Link, Navigate } from "react-router-dom";
import axios from 'axios';

class SignUp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            nickname: '',
            password: '',
            confirmPassword: '',
            role: 'USER',
            isRegistered: false
        };
    }

    handleInputChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const { email, nickname, password, confirmPassword, role } = this.state;

        if (password !== confirmPassword) {
            alert("Passwords do not match");
            return;
        }

        try {
            const checkResponse = await axios.post('http://localhost:8080/api/user/check', {
                email,
                nickname
            });

            if (checkResponse.data.emailExists) {
                alert("Email already exists");
                return;
            }

            if (checkResponse.data.nicknameExists) {
                alert("Nickname already exists");
                return;
            }

            const response = await axios.post('http://localhost:8080/api/auth/register', {
                email,
                nickname,
                password,
                role
            });

            console.log(response.data);
            this.setState({ isRegistered: true });
        } catch (error) {
            console.error('There was an error!', error);
        }
    }

    render() {
        if (this.state.isRegistered) {
            return <Navigate to="/signin" />
        }
        return (
            <div className="signup-container">
                <div className="logo-container">
                    <img src="logo_gamerise.png" alt="logo" />
                    <h2>Welcome to Gamerise!</h2>
                </div>
                <div className="signup-form">
                    <h2>Sign up</h2>
                    <form onSubmit={this.handleSubmit}>
                        <label>
                            <StyledInput type="text" name="email" Icon={FaEnvelope} placeholder={"E-mail"} onChange={this.handleInputChange} />
                        </label>
                        <label>
                            <StyledInput type="text" name="nickname" Icon={FaUser} placeholder={"Nickname"} onChange={this.handleInputChange} />
                        </label>
                        <label>
                            <StyledInput type="password" name="password" Icon={FaKey} placeholder={"Password"} onChange={this.handleInputChange} />
                        </label>
                        <label>
                            <StyledInput type="password" name="confirmPassword" Icon={FaKey} placeholder={"Confirm password"} onChange={this.handleInputChange} />
                        </label>
                        <Button className="button" type="submit" text="Sign up" />
                    </form>
                    <h3>Already have an account? <Link to="/signin" className="highlight">Sign in</Link></h3>
                </div>
            </div>
        );
    }
}

export default SignUp;
