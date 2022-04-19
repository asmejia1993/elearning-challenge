package com.modak.elearning.user;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    
    private final UserRepository userRepository;

    /**
     * Create a new user
     * 
     * @param userRequest
     * @return User
     */
    public User createUser(UserRequest userRequest) {
        User newUser = User.builder()
                            .email(userRequest.getEmail())
                            .firstName(userRequest.getFirstName())
                            .lastName(userRequest.getLastName())
                            .nickName(userRequest.getNickName())
                            .build();
        
        userRepository.saveAndFlush(newUser);
        return newUser;
    }

    /**
     * Get all lessons by user
     * 
     * @param id
     * @return User
     */
    public User getLessonByUser(Long id) {
        return userRepository.getById(id);
    }

    /**
     * Find all user from system
     * 
     * @return List<User>
     */
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAllWithoutLessons();
        log.info("users {}", users);
        return users;
    }
}
