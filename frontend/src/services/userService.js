// src/services/userService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/users'; // Ajustar si cambia el puerto

// Registrar nuevo usuario
export const registerUser = async (userData) => {
  try {
    const response = await axios.post(API_URL, userData); // POST /api/users
    return response.data;
  } catch (error) {
    throw new Error(error.response?.data?.message || 'Error en el registro');
  }
};

// Obtener todos los usuarios
export const getAllUsers = async () => {
  try {
    const response = await axios.get(API_URL); // GET /api/users
    return response.data;
  } catch (error) {
    throw new Error('Error al obtener usuarios');
  }
};

// Buscar usuario por email
export const getUserByEmail = async (email) => {
  try {
    const response = await axios.get(`${API_URL}/find`, {
      params: { email }, // GET /api/users/find?email=...
    });
    return response.data;
  } catch (error) {
    throw new Error('Usuario no encontrado');
  }
};

// Verificación simple (test endpoint opcional)
export const testConnection = async () => {
  try {
    const response = await axios.get(`${API_URL}/test`);
    return response.data;
  } catch (error) {
    throw new Error('No se pudo conectar con el backend');
  }
};
