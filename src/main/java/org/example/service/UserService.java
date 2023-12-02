package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.dtos.UserDTO;
import org.example.model.entities.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User createUser(UserDTO userDTO){
        User user = new User(userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getEmail());
        return userRepository.save(user);
    }

    public User findUserById(Long id){

        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO){
        // retrieve the existing user from the repository
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // update the fields of the existing user with data from the DTO
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());

        // call the method from the repository to persist the changes
        User updatedUser = new User(id, existingUser.getUsername(), existingUser.getEmail());

        // map the updated user entity to a DTO before returning it
        return userMapper.mapUserEntityToUserDTO(updatedUser);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }


//    public List<UserDTO> findUsersByUsername(String username){
//        return userRepository.findByUsername(username).stream()
//                .map(entity ->userMapper.mapUserEntityToUserDTO(entity))
//                .collect(Collectors.toList());
//
//    }
//
//    public List<UserDTO> findUsersByEmail(String email){
//        return userRepository.findByEmail(email).stream()
//                .map(entity ->userMapper.mapUserEntityToUserDTO(entity))
//                .collect(Collectors.toList());
//    }




}
