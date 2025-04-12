// src/pages/RegistroUsuario.js
import React, { useState } from 'react';
import { registrarUsuario } from '../services/usuarioService';

function RegistroUsuario() {
  const [usuario, setUsuario] = useState({ nombre: '', correo: '', contrasena: '' });

  const handleChange = (e) => {
    setUsuario({ ...usuario, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = await registrarUsuario(usuario);
      console.log('Usuario registrado:', data);
      alert('Usuario registrado con éxito');
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input name="nombre" value={usuario.nombre} onChange={handleChange} placeholder="Nombre" />
      <input name="correo" type="email" value={usuario.correo} onChange={handleChange} placeholder="Correo" />
      <input name="contrasena" type="password" value={usuario.contrasena} onChange={handleChange} placeholder="Contraseña" />
      <button type="submit">Registrarse</button>
    </form>
  );
}

export default RegistroUsuario;
