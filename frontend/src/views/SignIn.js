import React from 'react';
import '../styles/SignIn.css';
import {FaEnvelope, FaKey} from 'react-icons/fa';
import StyledInput from '../components/StyledInput';
import Button from '../components/Button';
import {Link, Navigate} from "react-router-dom";
import axios from 'axios';

class SignIn extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            isLoggedIn: false
        };
    }

    handleInputChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const { email, password } = this.state;
        try {
            const response = await axios.post('http://localhost:8080/api/auth/authenticate', {
                email,
                password
            });

            localStorage.setItem('token', response.data.token);

            this.setState({ isLoggedIn: true });
        } catch (error) {
            alert('Incorrect email or password.');
        }
    }

    render() {
        if (this.state.isLoggedIn) {
            return <Navigate to="/homepage" />
        }
        return (
            <div className="signin-container">
                <div className="logo-container">
                    <img src="logo_gamerise.png" alt="logo" />
                    <h2>Welcome to Gamerise!</h2>
                </div>
                <div className="signin-form">
                    <h2>Sign in</h2>
                    <form onSubmit={this.handleSubmit}>
                        <label>
                            <StyledInput type="text" name="email" Icon={FaEnvelope} placeholder={"E-mail"} onChange={this.handleInputChange} />
                        </label>
                        <label>
                            <StyledInput type="password" name="password" Icon={FaKey} placeholder={"Password"} onChange={this.handleInputChange} />
                        </label>
                        <Button className="button" type="submit" text="Sign in" />
                    </form>
                    <h3>Don't have an account? <Link to="/signup" className="highlight">Sign up</Link></h3>
                </div>
            </div>
        );
    }
}

export default SignIn;