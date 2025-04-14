package com.unbosque.edu.co.forest.service;

import com.unbosque.edu.co.forest.model.dto.UserDTO;
import com.unbosque.edu.co.forest.model.entity.User;
import com.unbosque.edu.co.forest.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }
}
