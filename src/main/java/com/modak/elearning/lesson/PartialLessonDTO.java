package com.modak.elearning.lesson;

import com.modak.elearning.lessoncategory.LessonCategory;

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
public class PartialLessonDTO {
    private Long id;
    private LessonCategory lessonCategory;
}
