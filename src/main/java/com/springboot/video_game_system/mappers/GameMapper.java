package com.springboot.video_game_system.mappers;

import com.springboot.video_game_system.dtos.GameMinResponseDto;
import com.springboot.video_game_system.dtos.GameRequestDto;
import com.springboot.video_game_system.dtos.GameResponseDto;
import com.springboot.video_game_system.models.Game;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public GameMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GameMinResponseDto toGameMinResponseDto(Game game){
        return modelMapper.map(game, GameMinResponseDto.class);
    }

    public GameResponseDto toGameRequestDto(Game game) {
        return modelMapper.map(game, GameResponseDto.class);
    }

    public Game toGame(GameRequestDto gameRequestDto) {
        return modelMapper.map(gameRequestDto, Game.class);
    }

}
