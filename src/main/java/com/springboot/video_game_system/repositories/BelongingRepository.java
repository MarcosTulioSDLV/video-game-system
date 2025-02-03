package com.springboot.video_game_system.repositories;

import com.springboot.video_game_system.models.Belonging;
import com.springboot.video_game_system.models.BelongingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BelongingRepository extends JpaRepository<Belonging, BelongingId> {



}
