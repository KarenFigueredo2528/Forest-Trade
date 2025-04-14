package com.unbosque.edu.co.forest.controller;

import com.unbosque.edu.co.forest.model.entity.User;
import com.unbosque.edu.co.forest.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // para permitir que React acceda
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> obtenerUsuarios() {
        return service.listarTodos();
    }

    @PostMapping
    public User registrarUsuario(@RequestBody User user) {
        return service.crearUsuario(user);
    }

    @GetMapping("/buscar")
    public User buscarPorCorreo(@RequestParam String correo) {
        return service.buscarPorCorreo(correo);
    }

    @GetMapping("/test")
    public String test() {
        return "Hola desde el backend";
    }

}
