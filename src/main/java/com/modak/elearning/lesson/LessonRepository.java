package com.modak.elearning.lesson;

import java.util.List;

import com.modak.elearning.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByUser(User user);
} 