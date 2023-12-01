package org.example.repository;

import org.example.model.entities.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepository {
    private final Map<Long, User> users = new HashMap<>();
    private long currentId = 1;

    public User save(User user){
        if(user.getId() == null){
            user.setId(currentId++);
        }
        users.put(user.getId(), user);
        return user;
    }

    public User update(User updatedUser){
        Long userId = updatedUser.getId();
        if( userId == null || !users.containsKey(userId) ){
            throw new RuntimeException("User not found for update");
        }
        users.put(userId, updatedUser);
        return updatedUser;
    }

    public Optional<User> findById(Long id){
        return Optional.ofNullable(users.get(id));
    }

    public List<User> findUserByUsername(String username){
       return users.values().stream()
               .filter( user -> user.getUsername().contains(username) )
               .collect(Collectors.toList() );
    }

    public List<User> findUserByEmail(String email){
        return users.values().stream()
                .filter( user -> user.getUsername().contains(email) )
                .collect(Collectors.toList() );
    }
    public void deleteById(Long id){
        users.remove(id);
    }


}
