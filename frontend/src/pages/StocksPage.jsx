import React, { useEffect, useState } from 'react';
import StockCard from '../components/StockCard';
import { fetchStocks } from '../services/stockService';
import './StocksPage.css';

const StocksPage = () => {
    const [stocks, setStocks] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        setIsLoading(true);
        fetchStocks()
            .then((data) => setStocks(data))
            .catch(console.error)
            .finally(() => setIsLoading(false));
    }, []);

    return (
        <div>
            <h2 style={{ textAlign: 'center' }}>Stock List</h2>
            {isLoading ? (
                    <div style={{ textAlign: 'center', marginTop: '20px' }}>
                        <span>Cargando acciones...</span>
                        {/* Puedes usar un spinner si ya tienes uno */}
                        <div className="loader" />
                    </div>
                ) : (
            <div className="stock-grid">
                {stocks.map(stock => (
                    <StockCard key={stock.symbol} stock={stock} />
                ))}
            </div>
            )}
        </div>
    );
};

export default StocksPage;