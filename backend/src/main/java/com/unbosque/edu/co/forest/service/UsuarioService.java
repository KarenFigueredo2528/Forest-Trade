package com.unbosque.edu.co.forest.service;

import com.unbosque.edu.co.forest.model.Usuario;
import com.unbosque.edu.co.forest.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario crearUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario buscarPorCorreo(String correo) {
        return repository.findByCorreo(correo);
    }
}
