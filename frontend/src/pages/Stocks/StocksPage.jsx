// src/pages/Stocks/StocksPage.jsx

import React, { useEffect, useState } from 'react';
import DashboardLayout from '../../layouts/DashboardLayout';
import StockCard from '../../components/StockCard/StockCard';
import { fetchStocks } from '../../services/stockService';
import styles from './StocksPage.module.css'; // Importamos los estilos

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
        <DashboardLayout>
            <div>
                {isLoading ? (
                    <div className={styles.loader}>
                        <span>Cargando acciones...</span>
                    </div>
                ) : (
                    <div className={styles['stock-grid']}>
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
