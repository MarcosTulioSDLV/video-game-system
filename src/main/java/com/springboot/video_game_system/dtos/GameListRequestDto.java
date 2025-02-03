package com.springboot.video_game_system.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GameListRequestDto {

    @NotBlank
    private String name;

}
