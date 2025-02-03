package com.springboot.video_game_system.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BelongingResponseDto {

    private GameResponseDto gameResponseDto;

    private GameListResponseDto gameListResponseDto;

    private Integer position;

}
