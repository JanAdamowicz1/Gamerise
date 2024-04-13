import React from 'react';
import '../styles/SignIn.css';
import {FaEnvelope, FaKey} from 'react-icons/fa';
import StyledInput from './StyledInput';
import Button from './Button';
import {Link} from "react-router-dom";
class SignIn extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div className="signin-container">
                <div className="logo-container">
                    <img src="logo_gamerise.png" alt="logo" />
                    <h2>Welcome to Gamerise!</h2>
                </div>
                <div className="signin-form">
                    <h2>Sign in</h2>
                    <form>
                        <label>
                            <StyledInput type="text" name="email" Icon={FaEnvelope} placeholder={"E-mail"} />
                        </label>
                        <label>
                            <StyledInput type="password" name="password" Icon={FaKey} placeholder={"Password"}/>
                        </label>
                        <Link to="/homepage">
                            <Button className="button" type="submit" text="Sign in" />
                        </Link>
                    </form>
                    <h3>Don't have an account? <Link to="/signup" className="highlight">Sign up</Link></h3>
                </div>
            </div>
        );
    }
}
export default SignIn;