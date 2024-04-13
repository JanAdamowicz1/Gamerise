import React from 'react';
import '../styles/StyledInput.css';

const StyledInput = ({ type, name, onChange, Icon, placeholder }) => {
    return (
        <div className="styled-input">
            <Icon className="input-icon" />
            <input className="styled-input-field" type={type} name={name} onChange={onChange} placeholder={placeholder}/>
        </div>
    );
}

export default StyledInput;