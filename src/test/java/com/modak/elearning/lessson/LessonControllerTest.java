package com.modak.elearning.lessson;

import com.modak.elearning.lesson.Lesson;
import com.modak.elearning.lesson.LessonController;
import com.modak.elearning.lesson.LessonRequest;
import com.modak.elearning.lesson.LessonService;
import com.modak.elearning.lessoncategory.LessonCategory;
import com.modak.elearning.user.User;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LessonController.class)
class LessonControllerTest {

    @MockBean
    private LessonService lessonService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should Show The Lesson Object when making Post request to endpoint - /api/v1/lesson")
    void ShouldCreateLesson() throws Exception {
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
        Lesson lesson = Lesson.builder()
                                .id(1l)
                                .lessonCategory(lessonCategory)
                                .user(user)
                                .build();
        LessonRequest lessonRequest = new LessonRequest(1L, 1L);
        
        Mockito.when(lessonService.createLesson(lessonRequest)).thenReturn(lesson);
        mockMvc.perform(post("/api/v1/lesson"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].id", Matchers.is(1L)))
                .andExpect(jsonPath("$[0].lessonCategory.$[0].id", Matchers.is(1l)));
    }
}
