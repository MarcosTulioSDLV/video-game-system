package com.springboot.video_game_system.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
public class GameMinResponseDto {

    private Long id;

    private String title;

    private Integer releaseYear;

    private String imgUrl;

    private String shortDescription;

    public GameMinResponseDto(){
    }

}
