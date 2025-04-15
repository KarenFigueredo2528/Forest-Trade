import React, { useEffect, useState } from 'react';
import DashboardLayout from '../../layouts/DashboardLayout';
import StockCard from '../../components/StockCard/StockCard';
import { fetchStocks } from '../../services/stockService';
import './StocksPage.module.css';

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

    console.log(stocks);
    
    return (
        <DashboardLayout>
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
        </DashboardLayout>
    );
};

export default StocksPage;