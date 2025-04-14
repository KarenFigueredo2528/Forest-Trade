package com.unbosque.edu.co.forest.service;

import com.unbosque.edu.co.forest.model.entity.User;
import com.unbosque.edu.co.forest.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UserRepository repository;

    public UsuarioService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> listarTodos() {
        return repository.findAll();
    }

    public User crearUsuario(User user) {
        return repository.save(user);
    }

    public User buscarPorCorreo(String correo) {
        return repository.findByCorreo(correo);
    }
}
