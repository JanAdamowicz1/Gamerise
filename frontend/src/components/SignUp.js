import React from 'react';
import '../styles/SignUp.css';
import {FaEnvelope, FaKey, FaUser} from 'react-icons/fa';
import StyledInput from './StyledInput';
import Button from './Button';
import {Link} from "react-router-dom";
class SignUp extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div className="signup-container">
                <div className="logo-container">
                    <img src="logo_gamerise.png" alt="logo" />
                    <h2>Welcome to Gamerise!</h2>
                </div>
                <div className="signup-form">
                    <h2>Sign up</h2>
                    <form>
                        <label>
                            <StyledInput type="text" name="email" Icon={FaEnvelope} placeholder={"E-mail"} />
                        </label>
                        <label>
                            <StyledInput type="text" name="username" Icon={FaUser} placeholder={"Username"} />
                        </label>
                        <label>
                            <StyledInput type="password" name="password" Icon={FaKey} placeholder={"Password"}/>
                        </label>
                        <label>
                            <StyledInput type="password" name="confirmPassword" Icon={FaKey} placeholder={"Confirm password"} />
                        </label>
                        <Link to="/homepage">
                            <Button className="button" type="submit" text="Sign up" />
                        </Link>
                    </form>
                    <h3>Already have an account? <Link to="/signin" className="highlight">Sign in</Link></h3>
                </div>
            </div>
        );
    }
}
export default SignUp;