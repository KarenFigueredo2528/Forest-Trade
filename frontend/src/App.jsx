import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

// Pages
import StockPage from './pages/Stocks/StocksPage';
import RegisterPage from './pages/Register/RegisterPage';
// Puedes añadir más páginas luego como:
// import LoginPage from './pages/LoginPage';
// import SettingsPage from './pages/SettingsPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<StockPage />} />
        <Route path="/register" element={<RegisterPage />} />
      </Routes>
    </Router>
  );
}

export default App;
