import React from 'react';
import Sidebar from './Sidebar';
import Header from './Header'; // opcional para estilos globales

const DashboardLayout = ({ children }) => {
  return (
    <div className="dashboard-container">
      <Sidebar />
      <div className="main-content">
        <Header />
        <div className="page-content">
          {children}
        </div>
      </div>
    </div>
  );
};

export default DashboardLayout;
