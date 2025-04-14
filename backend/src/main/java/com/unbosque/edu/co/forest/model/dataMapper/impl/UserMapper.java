package com.unbosque.edu.co.forest.model.dataMapper.impl;

import com.unbosque.edu.co.forest.model.dataMapper.BaseMapper;
import com.unbosque.edu.co.forest.model.dto.UserDTO;
import com.unbosque.edu.co.forest.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<User, UserDTO> {

    @Override
    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        return user;
    }
}