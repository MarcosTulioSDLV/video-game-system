package com.springboot.video_game_system.repositories;

import com.springboot.video_game_system.models.Game;
import com.springboot.video_game_system.projections.GameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {

    @Query(nativeQuery = true,value = """
            SELECT TB_GAME.id,  TB_GAME.title,  TB_GAME.release_year AS `releaseYear`,  TB_GAME.img_url AS imgUrl,
            		 TB_GAME.short_description AS shortDescription,TB_BELONGING.position\s
            FROM TB_GAME INNER JOIN TB_BELONGING ON TB_GAME.id =TB_BELONGING.game_id WHERE TB_BELONGING.game_list_id =:gameListId 
            ORDER BY TB_BELONGING.position""")
    List<GameProjection> getGameWithPositionListByGameListId(Long gameListId);

    boolean existsByTitle(String title);

}
