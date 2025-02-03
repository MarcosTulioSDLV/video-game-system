package com.springboot.video_game_system.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class GameWithPositionResponseDto {

    //Game class attributes
    private Long id;

    private String title;

    private Integer releaseYear;

    private String imgUrl;

    private String shortDescription;
    //-----

    //Belonging class attributes
    private Integer position;
}
