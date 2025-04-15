import React from 'react';

const StockCard = ({ stock }) => {
    const isPositive = stock.change > 0;

    return (
        <div className="stock-card">
            <h3>{stock.stockName} ({stock.symbol})</h3>
            <p>Price: ${stock.currentPrice}</p>
            <p>Change:
                <span style={{ color: isPositive ? 'green' : 'red' }}>
                    {isPositive ? '▲' : '▼'} ${stock.change.toFixed(2)}
                </span>
            </p>
        </div>
    );
};

export default StockCard;
