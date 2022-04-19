package com.modak.elearning.lesson;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/lesson")
@AllArgsConstructor
public class LessonController {
    
    private final LessonService lessonService;

    @PostMapping
    @ResponseBody
    public Lesson createLesson(@RequestBody LessonRequest request) {
        return lessonService.createLesson(request);
    }

}
