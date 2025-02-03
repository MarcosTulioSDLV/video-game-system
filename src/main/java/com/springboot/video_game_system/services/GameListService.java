package com.springboot.video_game_system.services;

import com.springboot.video_game_system.dtos.GameListRequestDto;
import com.springboot.video_game_system.dtos.GameListResponseDto;
import com.springboot.video_game_system.dtos.ReplacementRequestDto;
import com.springboot.video_game_system.models.GameList;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface GameListService {

    Page<GameListResponseDto> getAllGameLists(Pageable pageable);

    GameListResponseDto getGameListById(Long gameListId);

    GameList findGameListById(Long gameListId);

    @Transactional
    void addGameList(GameListRequestDto gameListRequestDto);

    @Transactional
    void moveGameList(Long gameListId, ReplacementRequestDto replacementRequestDto);

    @Transactional
    void updateGameList(Long id, GameListRequestDto gameListRequestDto);

    @Transactional
    void removeGameList(Long id);
}
