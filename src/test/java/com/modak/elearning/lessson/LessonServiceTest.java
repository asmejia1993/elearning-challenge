package com.modak.elearning.lessson;

import static org.mockito.Mockito.verify;

import java.util.Optional;

import com.modak.elearning.lesson.Lesson;
import com.modak.elearning.lesson.LessonRepository;
import com.modak.elearning.lesson.LessonService;
import com.modak.elearning.lesson.LessonRequest;
import com.modak.elearning.lessoncategory.LessonCategory;
import com.modak.elearning.lessoncategory.LessonCategoryRepository;
import com.modak.elearning.user.User;
import com.modak.elearning.user.UserRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Captor;

@ExtendWith(MockitoExtension.class)
class LessonServiceTest {
    
    @Mock 
    LessonRepository lessonRepository;
    private LessonService lessonService;
    @Mock 
    private UserRepository userRepository;
    @Mock 
    private LessonCategoryRepository lessonCategoryRepository;
    @Captor
    private ArgumentCaptor<Lesson> lessonArgumentCaptor;

    @BeforeEach
    void setUp() {
        lessonService = new LessonService(lessonRepository, lessonCategoryRepository, userRepository);
    }

    @Test
    @DisplayName("Should Save Lesson")
    void shouldCreateLesson() {
        LessonCategory lessonCategory = LessonCategory.builder()
                                                        .id(1L)
                                                        .name("Lesson 1")
                                                        .build();
        User user = User.builder()
                        .email("test@email.com")
                        .id(1L)
                        .firstName("John")
                        .lastName("Wick")
                        .nickName("wick88")
                        .build();
        LessonRequest lessonRequest = new LessonRequest(1L, 1L);

        Mockito.when(lessonCategoryRepository.findById(1L)).thenReturn(Optional.of(lessonCategory));
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        
        lessonService.createLesson(lessonRequest);
        verify(lessonRepository, Mockito.times(1)).saveAndFlush(lessonArgumentCaptor.capture());
        
        Lesson lessonArgument = lessonArgumentCaptor.getValue();
        Assertions.assertThat(lessonArgument.getLessonCategory().getId()).isEqualTo(1L);
        Assertions.assertThat(lessonArgument.getUser().getEmail()).isEqualTo("test@email.com");
    }

}
