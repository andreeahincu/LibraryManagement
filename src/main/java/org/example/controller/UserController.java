package org.example.controller;

import jakarta.validation.Valid;
import org.aspectj.weaver.patterns.HasMemberTypePatternForPerThisMatching;
import org.example.model.dtos.CustomResponseDTO;
import org.example.model.dtos.UserDTO;
import org.example.model.entities.User;
import org.example.service.UserMapper;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "/createUser")
    public ResponseEntity<CustomResponseDTO> createUser(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        // check for validation errors
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseObject(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }
        UserDTO createdUser = userMapper.mapUserEntityToUserDTO(userService.createUser(userDTO));

        // set response data
        customResponseDTO.setResponseObject(createdUser);
        customResponseDTO.setResponseMessage("User created successfully");

        // return a response entity with data and HTTP status
        return new ResponseEntity<>(customResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<CustomResponseDTO> getUserById(@PathVariable Long id) {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        UserDTO foundUserById = userMapper.mapUserEntityToUserDTO(userService.findUserById(id));

        if (Objects.isNull(foundUserById)) {
            customResponseDTO.setResponseMessage("No user was found by this id");
            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
        }
        customResponseDTO.setResponseObject(foundUserById);
        customResponseDTO.setResponseMessage("User found successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/updateUser/{userId}")
    public ResponseEntity<CustomResponseDTO> updateUser(@PathVariable Long userId, @RequestBody @Valid UserDTO userDTO, BindingResult bindingResult){
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        if( bindingResult.hasErrors() ){
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }

        try{
            UserDTO updatedUser = userService.updateUser(userId, userDTO);

            customResponseDTO.setResponseObject(updatedUser);
            customResponseDTO.setResponseMessage("User updated successfully");

            return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
        } catch(RuntimeException e){
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(e.getMessage());

            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity("User deleted", HttpStatus.OK);
    }

//    @GetMapping("/getUsersByUsername/{username}")
//    public ResponseEntity<CustomResponseDTO> getUsersByUsername(@PathVariable String username) {
//
//    CustomResponseDTO customResponseDTO = new CustomResponseDTO();
//
//    List<UserDTO> foundUsersByUsername = userService.findUsersByUsername(username);
//    if( Objects.isNull(foundUsersByUsername) || foundUsersByUsername.isEmpty() ){
//        customResponseDTO.setResponseMessage("No user was found by this username");
//        return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
//    }
//    customResponseDTO.setResponseObject(foundUsersByUsername);
//    customResponseDTO.setResponseMessage("User found successfully");
//    return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
//}
//
//    @GetMapping("/getUsersByEmail/{email}")
//    public ResponseEntity<CustomResponseDTO> getUsersByEmail(@PathVariable String email) {
//
//        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
//
//        List<UserDTO> foundUsersByEmail = userService.findUsersByUsername(email);
//        if( Objects.isNull(foundUsersByEmail) || foundUsersByEmail.isEmpty() ){
//            customResponseDTO.setResponseMessage("No user was found by this username");
//            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
//        }
//        customResponseDTO.setResponseObject(foundUsersByEmail);
//        customResponseDTO.setResponseMessage("User found successfully");
//        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
//    }




}
