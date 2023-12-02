package org.example.service;

import org.example.model.dtos.UserDTO;
import org.example.model.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User mapUserDTOToUserEntity(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    public UserDTO mapUserEntityToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(userDTO.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
