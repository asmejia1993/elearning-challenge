package com.modak.elearning.user;

import java.util.List;

import com.modak.elearning.lesson.Lesson;
import com.modak.elearning.lesson.LessonRepository;
import com.modak.elearning.lesson.PartialLessonDTO;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

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
    public LessonByUserDTO getLessonByUser(Long id) {

        User user = userRepository.getById(id);
        List<PartialLessonDTO> partialLessonDTO = lessonRepository.findByUser(user)
                                                .stream()
                                                .map(x ->
                                                    PartialLessonDTO.builder()
                                                        .id(x.getId())
                                                        .lessonCategory(x.getLessonCategory())
                                                        .build())
                                                .toList();
        return LessonByUserDTO.builder()
                    .user(user)
                    .lessons(partialLessonDTO)
                    .build();
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
