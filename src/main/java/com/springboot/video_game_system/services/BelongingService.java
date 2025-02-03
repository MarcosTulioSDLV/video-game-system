package com.springboot.video_game_system.services;

import com.springboot.video_game_system.dtos.BelongingRequestDto;
import com.springboot.video_game_system.dtos.BelongingResponseDto;
import com.springboot.video_game_system.dtos.ReplacementRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public interface BelongingService {

    BelongingResponseDto getBelongingById(Long gameId,Long gameListId);

    @Transactional
    void addBelonging(BelongingRequestDto belongingRequestDto);

    @Transactional
    void moveGameList(Long gameListId, ReplacementRequestDto replacementRequestDto);

    @Transactional
    void removeBelonging(Long gameId,Long gameListId);

}
