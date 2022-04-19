package com.modak.elearning.user;

import java.util.ArrayList;
import java.util.List;

import com.modak.elearning.lesson.Lesson;
import com.modak.elearning.lessoncategory.LessonCategory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    // @BeforeEach
    // void setUp() {
    //     userService = new UserService(userRepository);
    // }
    
    @Test
    @DisplayName("Should create user")
    void shouldCreateUser() {
        final LessonCategory lessonCategory = LessonCategory.builder()
                                                        .id(1L)
                                                        .name("Lesson 1")
                                                        .build();
        final Lesson lesson = Lesson.builder()
                                    .id(1l)
                                    .lessonCategory(lessonCategory)
                                    .build();
        final List<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson);
        final User user = User.builder()
                                .id(1L)
                                .email("test@email.com")
                                .firstName("John")
                                .lastName("Wick")
                                .nickName("john77")
                                .lessons(lessons)
                                .build();
        UserRequest userRequest = UserRequest.builder()
                                            .email("test@email.com")
                                            .firstName("John")
                                            .lastName("Wick")
                                            .nickName("john77")
                                            .build();
        
        given(userRepository.saveAndFlush(user)).willReturn(user);
        User savedUser = userService.createUser(userRequest);
        
        Assertions.assertThat(savedUser).isNotNull();
        verify(userRepository).saveAndFlush(any(User.class));
    }

}
