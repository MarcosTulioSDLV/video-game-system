package com.springboot.video_game_system.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BelongingRequestDto {

    @NotNull @Positive
    private Long gameId;

    @NotNull @Positive
    private Long gameListId;

}
