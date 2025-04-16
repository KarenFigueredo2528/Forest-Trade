// src/components/StockCard/StockCard.jsx

import React from 'react';
import styles from './StockCard.module.css'; // Importamos los estilos

const StockCard = ({ stock }) => {
    const isPositive = stock.change > 0;

    return (
        <div className={styles['stock-card']}>
            <h3>{stock.stockName} ({stock.symbol})</h3>
            <p className={styles.price}>Price: ${stock.currentPrice}</p>
            <p className={styles.change}>
                Change: 
                <span className={isPositive ? styles.change : styles['change-negative']}>
                    {isPositive ? '▲' : '▼'} ${stock.change.toFixed(2)}
                </span>
            </p>
        </div>
    );
};

export default StockCard;
