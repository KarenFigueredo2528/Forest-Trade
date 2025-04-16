// src/layouts/DashboardLayout.jsx

import React, { useState } from 'react';
import Header from './Header/Header';
import Sidebar from './Sidebar/Sidebar';
import styles from './DashboardLayout.module.css';

const DashboardLayout = ({ children }) => {
  const [sidebarVisible, setSidebarVisible] = useState(true);

  const toggleSidebar = () => {
    setSidebarVisible(!sidebarVisible);
  };

  return (
    <div className={styles.layout}>
      <Sidebar isVisible={sidebarVisible} />
      <div
        className={`${styles.mainContent} ${
          !sidebarVisible ? styles.mainContentCollapsed : ''
        }`}
      >
        <Header toggleSidebar={toggleSidebar} />
        <div className={styles.content}>{children}</div>
      </div>
    </div>
  );
};

export default DashboardLayout;
