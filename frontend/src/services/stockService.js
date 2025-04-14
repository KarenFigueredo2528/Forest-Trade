export const fetchStocks = async () => {
    const response = await fetch('http://localhost:8080/api/stocks');
    const data = await response.json();
    return data;
};
