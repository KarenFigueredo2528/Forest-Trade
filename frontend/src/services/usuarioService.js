// src/services/usuarioService.js
export const registrarUsuario = async (usuario) => {
    const response = await fetch('http://localhost:8080/api/usuarios', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(usuario)
    });
  
    if (!response.ok) throw new Error('Error registrando usuario');
  
    return await response.json();
  };  