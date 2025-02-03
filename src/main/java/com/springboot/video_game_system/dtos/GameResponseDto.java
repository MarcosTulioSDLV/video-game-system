package com.springboot.video_game_system.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class GameResponseDto {

    private Long id;

    private String title;

    private Integer releaseYear;

    private String genre;

    private String platforms;

    private Double score;

    private String imgUrl;

    private String shortDescription;

    private String longDescription;

}
