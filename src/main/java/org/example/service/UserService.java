package org.example.service;

import org.example.model.dtos.UserDTO;
import org.example.model.entities.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User createUser(UserDTO userDTO){
        User user = new User(userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getEmail());
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserDTO userDTO){
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException(("User not found"));
         existingUser = new User(userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getEmail());
         return userRepository.update(existingUser);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }


}
