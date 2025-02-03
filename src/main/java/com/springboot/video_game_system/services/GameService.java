package com.springboot.video_game_system.services;

import com.springboot.video_game_system.dtos.GameMinResponseDto;
import com.springboot.video_game_system.dtos.GameRequestDto;
import com.springboot.video_game_system.dtos.GameResponseDto;
import com.springboot.video_game_system.dtos.GameWithPositionResponseDto;
import com.springboot.video_game_system.models.Game;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {

    Page<GameMinResponseDto> getAllGames(Pageable pageable);

    GameResponseDto getGameById(Long id);

    Game findGameById(Long id);

    List<GameMinResponseDto> getGamesByGameListId(Long gameListId);

    @Transactional
    List<GameMinResponseDto> getGamesByGameListIdV2(Long gameListId);

    @Transactional
    List<GameWithPositionResponseDto> getGameWithPositionListByGameListId(Long gameListId);

    @Transactional
    void addGame(GameRequestDto gameRequestDto);

    @Transactional
    void updateGame(Long id, GameRequestDto gameRequestDto);

    @Transactional
    void removeGame(Long id);
}
