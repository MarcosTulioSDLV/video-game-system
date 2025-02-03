package com.springboot.video_game_system.mappers;

import com.springboot.video_game_system.dtos.GameListRequestDto;
import com.springboot.video_game_system.dtos.GameListResponseDto;
import com.springboot.video_game_system.models.GameList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameListMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public GameListMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GameListResponseDto toGameListResponseDto(GameList gameList){
        return modelMapper.map(gameList, GameListResponseDto.class);
    }

    public GameList toGameList(GameListRequestDto gameListRequestDto) {
        return modelMapper.map(gameListRequestDto, GameList.class);
    }
}
