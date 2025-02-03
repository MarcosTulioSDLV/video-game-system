package com.springboot.video_game_system.repositories;

import com.springboot.video_game_system.models.GameList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameListRepository extends JpaRepository<GameList,Long> {

    @Modifying
    @Query(nativeQuery = true,value = """
            UPDATE TB_BELONGING SET position=:newPosition 
            WHERE (TB_BELONGING.game_list_id=:gameListId AND TB_BELONGING.game_id=:gameId) 
            """)
    void updateBelongingPosition(Long gameListId, Long gameId, Integer newPosition);

    boolean existsByName(String name);

}
