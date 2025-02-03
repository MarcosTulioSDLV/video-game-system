package com.springboot.video_game_system.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReplacementRequestDto {

    @NotNull @PositiveOrZero
    private Integer sourceIndex;

    @NotNull @PositiveOrZero
    private Integer destinationIndex;

}
