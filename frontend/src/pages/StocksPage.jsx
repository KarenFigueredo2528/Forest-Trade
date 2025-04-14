import React, { useEffect, useState } from 'react';
import StockCard from '../components/StockCard';
import { fetchStocks } from '../services/stockService';
import './StocksPage.css';

const StocksPage = () => {
    const [stocks, setStocks] = useState([]);

    useEffect(() => {
        fetchStocks().then(setStocks).catch(console.error);
    }, []);

    return (
        <div>
            <h2 style={{ textAlign: 'center' }}>Stock List</h2>
            <div className="stock-grid">
                {stocks.map(stock => (
                    <StockCard key={stock.symbol} stock={stock} />
                ))}
            </div>
        </div>
    );
};

export default StocksPage;