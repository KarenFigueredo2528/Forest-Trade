import React from 'react';
import { Link } from 'react-router-dom';

const Sidebar = () => {
  return (
    <aside className="sidebar">
      <h2>ForestTrade</h2>
      <nav>
        <ul>
          <li>Dashboard</li>
          <li>Mis Acciones</li>
          <li>Alertas</li>
          <li>Configuración</li>
          <li><Link to="/register">Registrar Usuario</Link></li> {/* Nuevo ítem */}
          <li><Link to="/login">Iniciar Sesión</Link></li>
        </ul>
      </nav>
    </aside>
  );
};

export default Sidebar;
