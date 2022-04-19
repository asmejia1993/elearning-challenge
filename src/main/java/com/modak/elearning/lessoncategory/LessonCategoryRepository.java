package com.modak.elearning.lessoncategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonCategoryRepository extends JpaRepository<LessonCategory, Long> {
    
}
