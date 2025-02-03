package com.springboot.video_game_system.mappers;

import com.springboot.video_game_system.dtos.BelongingResponseDto;
import com.springboot.video_game_system.dtos.GameListResponseDto;
import com.springboot.video_game_system.dtos.GameResponseDto;
import com.springboot.video_game_system.models.Belonging;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BelongingMapper {

    private final ModelMapper modelMapper;

    private final GameMapper gameMapper;

    private final GameListMapper gameListMapper;

    @Autowired
    public BelongingMapper(ModelMapper modelMapper, GameMapper gameMapper, GameListMapper gameListMapper) {
        this.modelMapper = modelMapper;
        this.gameMapper = gameMapper;
        this.gameListMapper = gameListMapper;
    }

    public BelongingResponseDto toBelongingResponseDto(Belonging belonging) {
        BelongingResponseDto belongingResponseDto= modelMapper.map(belonging,BelongingResponseDto.class);
        GameResponseDto gameResponseDto= gameMapper.toGameRequestDto(belonging.getGame());
        GameListResponseDto gameListResponseDto= gameListMapper.toGameListResponseDto(belonging.getGameList());
        belongingResponseDto.setGameResponseDto(gameResponseDto);
        belongingResponseDto.setGameListResponseDto(gameListResponseDto);
        return belongingResponseDto;
    }

}
