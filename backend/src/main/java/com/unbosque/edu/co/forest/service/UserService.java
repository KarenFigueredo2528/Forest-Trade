package com.unbosque.edu.co.forest.service;

import com.unbosque.edu.co.forest.model.dto.UserDTO;
import com.unbosque.edu.co.forest.model.entity.User;
import com.unbosque.edu.co.forest.model.enums.AccountStatus;
import com.unbosque.edu.co.forest.model.enums.Role;
import com.unbosque.edu.co.forest.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserDTO getUserById(Integer id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserDTO.class))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDTO getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .map(user -> modelMapper.map(user, UserDTO.class))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.findUserByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        if (userDTO.getRole() == null) {
            userDTO.setRole(Role.ADMIN);
        }
        if (userDTO.getAccountStatus() == null) {
            userDTO.setAccountStatus(AccountStatus.ACTIVE);
        }
        if (userDTO.getPasswordHash() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        userDTO.setPasswordHash(passwordEncoder.encode(userDTO.getPasswordHash()));
        System.out.println(userDTO);

        User user = modelMapper.map(userDTO, User.class);
        System.out.println(user);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    public boolean emailExists(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    public List<UserDTO> getUsersByRole(Role role) {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole() == role)
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public List<UserDTO> getUsersByStatus(AccountStatus status) {
        return userRepository.findAll().stream()
                .filter(user -> user.getAccountStatus() == status)
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public List<UserDTO> getUsersWithSubscription() {
        return userRepository.findAll().stream()
                .filter(User::getHasSubscription)
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public List<UserDTO> searchUsersByName(String name) {
        return userRepository.findAll().stream()
                .filter(user -> user.getName() != null && user.getName().toLowerCase().contains(name.toLowerCase()))
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO loginUser(String email, String rawPassword) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Correo no registrado"));

        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        UserDTO dto = modelMapper.map(user, UserDTO.class);
        dto.setPasswordHash(null); // Nunca devolvemos el hash al frontend
        return dto;
    }
}
