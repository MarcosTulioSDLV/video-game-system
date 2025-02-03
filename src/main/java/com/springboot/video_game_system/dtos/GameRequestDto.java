package com.springboot.video_game_system.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GameRequestDto {

    @NotBlank
    private String title;

    @NotNull @PositiveOrZero
    private Integer releaseYear;

    @NotBlank
    private String genre;

    @NotBlank
    private String platforms;

    @NotNull @PositiveOrZero
    private Double score;

    @NotBlank
    private String imgUrl;

    @NotBlank
    private String shortDescription;

    @NotBlank
    private String longDescription;

}
