import React, { useEffect, useState } from 'react';
import { Navigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';

const withAuth = (WrappedComponent) => {
    return (props) => {
        const [isTokenActive, setIsTokenActive] = useState(false);
        const [loading, setLoading] = useState(true);

        useEffect(() => {
            console.log('Checking token...');
            const token = localStorage.getItem('token');
            if (token) {
                const decodedToken = jwtDecode(token);
                const currentTime = Date.now() / 1000;
                console.log('Decoded token:', decodedToken);
                console.log('Current time:', currentTime);
                if (decodedToken.exp < currentTime) {
                    console.log('Token expired, removing...');
                    localStorage.removeItem('token');
                } else {
                    console.log('Token is active, setting state...');
                    setIsTokenActive(true);
                }
            }
            setLoading(false);
        }, []);

        if (loading) {
            return null;
        }

        if (!isTokenActive) {
            console.log('Token is not active, navigating to /signin');
            return <Navigate to="/signin" />
        }
        console.log('Rendering wrapped component');
        return <WrappedComponent {...props} />;
    }
}

export default withAuth;