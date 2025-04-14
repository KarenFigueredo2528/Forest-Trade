package com.unbosque.edu.co.forest.controller;

import com.unbosque.edu.co.forest.model.dto.UserDTO;
import com.unbosque.edu.co.forest.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // para permitir que React acceda
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDTO> obtenerUsuarios() {
        return service.getAllUsers();
    }

    @PostMapping
    public UserDTO registrarUsuario(@RequestBody UserDTO user) {
        return service.createUser(user);
    }

    @GetMapping("/find")
    public UserDTO findByEmail(@RequestParam String email) {
        return service.getUserByEmail(email);
    }

    @GetMapping("/test")
    public String test() {
        return "Hola desde el backend";
    }

}
