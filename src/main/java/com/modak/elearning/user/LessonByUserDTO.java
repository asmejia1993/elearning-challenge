package com.modak.elearning.user;

import java.util.List;

import com.modak.elearning.lesson.PartialLessonDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonByUserDTO {
    private User user;
    List<PartialLessonDTO> lessons; 
}

