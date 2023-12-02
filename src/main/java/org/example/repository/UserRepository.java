package org.example.repository;

import org.example.model.entities.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class UserRepository {
    private final Map<Long, User> users = new HashMap<>();
//    private User user1 = new User(113355L, "johnsmith", "johnsmith@library.com");
//    private User user2 = new User(335522L, "annejordan", "annejordan@library.com");
//    private User user3 = new User(772244L, "samsmy", "samsmy@library.com");


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
