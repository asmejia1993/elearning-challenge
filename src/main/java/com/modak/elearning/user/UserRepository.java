package com.modak.elearning.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query(value = """
                    select u.id, u.first_name, u.last_name,
                    u.email, u.nick_name, u.created_at, u.updated_at
                    from user u""", 
            nativeQuery = true)
    List<User> findAllWithoutLessons();
}
