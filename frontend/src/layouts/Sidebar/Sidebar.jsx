// src/layouts/Sidebar.jsx
import React from 'react';
import { Link } from 'react-router-dom';
import styles from './Sidebar.module.css';
import DashboardIcon from '../../assets/icons/DashboardIcon';

const Sidebar = ({ isVisible }) => {
  return (
    <aside className={`${styles.sidebar} ${isVisible ? styles.visible : styles.hidden}`}>
      <nav>
        <ul className={styles.navList}>
          <li className={styles.navItem}>
            <Link to="/" className={styles.navLink}><DashboardIcon></DashboardIcon> Dashboard</Link>
          </li>
          <li className={styles.navItem}>
            <Link to="/stocks" className={styles.navLink}>Acciones</Link>
          </li>
          <li className={styles.navItem}>
            <Link to="/alerts" className={styles.navLink}>Proveedores</Link>
          </li>
          <li className={styles.navItem}>
            <Link to="/register" className={styles.navLink}>Tradear Nuevo</Link>
          </li>
        </ul>
      </nav>
      
    </aside>
  );
};

export default Sidebar;
