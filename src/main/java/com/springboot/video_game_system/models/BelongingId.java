package com.springboot.video_game_system.models;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode
@Embeddable
public class BelongingId implements Serializable {

    private static final long serialVersionUID=1L;

    private Long gameId;

    private Long gameListId;

    public BelongingId(){
    }

}
