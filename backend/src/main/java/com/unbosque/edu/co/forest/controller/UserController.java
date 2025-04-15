package com.unbosque.edu.co.forest.controller;

import com.unbosque.edu.co.forest.model.dto.UserDTO;
import com.unbosque.edu.co.forest.model.enums.AccountStatus;
import com.unbosque.edu.co.forest.model.enums.Role;
import com.unbosque.edu.co.forest.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Allow React frontend
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // Get all users
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return service.getAllUsers();
    }

    // Register a new user
    @PostMapping
    public UserDTO registerUser(@RequestBody UserDTO user) {
        return service.createUser(user);
    }

    // Find by email
    @GetMapping("/find")
    public UserDTO getUserByEmail(@RequestParam String email) {
        return service.getUserByEmail(email);
    }

    // Check if email exists
    @GetMapping("/exists")
    public boolean checkIfEmailExists(@RequestParam String email) {
        return service.emailExists(email);
    }

    // Get users by role
    @GetMapping("/role")
    public List<UserDTO> getUsersByRole(@RequestParam Role role) {
        return service.getUsersByRole(role);
    }

    // Get users by account status
    @GetMapping("/status")
    public List<UserDTO> getUsersByStatus(@RequestParam AccountStatus status) {
        return service.getUsersByStatus(status);
    }

    // Get users with active subscriptions
    @GetMapping("/subscribed")
    public List<UserDTO> getSubscribedUsers() {
        return service.getUsersWithSubscription();
    }

    // Search users by name (partial match)
    @GetMapping("/search")
    public List<UserDTO> searchUsersByName(@RequestParam String name) {
        return service.searchUsersByName(name);
    }

    // Simple backend test endpoint
    @GetMapping("/test")
    public String test() {
        return "Hello from the backend";
    }
}
