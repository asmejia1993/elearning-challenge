package com.modak.elearning.lesson;

import com.modak.elearning.exception.BadRequestException;
import com.modak.elearning.lessoncategory.LessonCategory;
import com.modak.elearning.lessoncategory.LessonCategoryRepository;
import com.modak.elearning.user.User;
import com.modak.elearning.user.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class LessonService {
    
    private final LessonRepository lessonRepository;
    private final LessonCategoryRepository lessonCategoryRepository;
    private final UserRepository userRepository;

    /**
     * Create a new lesson
     * 
     * @param lessonRequest
     * @return Lesson
     */
    public Lesson createLesson(LessonRequest lessonRequest) {
        LessonCategory lessonCategory = lessonCategoryRepository
                                            .findById(lessonRequest.getLessonCategoryId())
                                            .orElseThrow(() -> 
                                                new BadRequestException
                                                (String.format("Category not valid: %d", lessonRequest.getLessonCategoryId())
                                            ));
        log.info("LessonCategory: {}", lessonCategory);

        User userFound = userRepository
                        .findById(lessonRequest.getUserId())
                        .orElseThrow(() -> 
                            new BadRequestException
                            (String.format("User not valid: %d", lessonRequest.getUserId()) 
                        ));                                 
        log.info("user: {} {}", userFound.getId(), userFound.getFirstName());
        Lesson newLesson = Lesson.builder()
                                    .lessonCategory(lessonCategory)
                                    .user(userFound)
                                    .build(); 
        
        lessonRepository.saveAndFlush(newLesson); 
        return newLesson;
    }
}
