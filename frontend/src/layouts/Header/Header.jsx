import React from 'react';
import styles from './Header.module.css';
import BinanceLogo from '../../assets/icons/binanceLogo';

const Header = ({ toggleSidebar }) => {
  return (
    <header className={styles.headerContainer}>
      <div className={styles.brand}>
        Forest Trade 
      </div>
      <div className={styles.actions}>
        <button className={styles.iconButton} onClick={toggleSidebar}>
          ☰
        </button>
        <button className={styles.button}>Depositar<BinanceLogo></BinanceLogo> </button>
        <a href="#" className={styles.link}>Configuración </a>
        <a href="#" className={styles.link}>Cerrar sesión</a>
      </div>
    </header>
  );
};

export default Header;
