package com.modak.elearning.friendship;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendShipRepository extends JpaRepository<FriendShip, Long> {
    
    @Query("SELECT f FROM FriendShip f WHERE f.sourceId = ?1")
    List<FriendShip> findBySourceId(Long sourceId);
}
