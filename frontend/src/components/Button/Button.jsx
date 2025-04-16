// src/components/Button/Button.jsx

import React from 'react';
import styles from './Button.module.css';

const Button = ({ text, variant = 'primary', onClick, className = '' }) => {
  return (
    <button
      className={`btn btn-${variant} ${styles.button} ${className}`}
      onClick={onClick}
    >
      {text}
    </button>
  );
};

export default Button;
