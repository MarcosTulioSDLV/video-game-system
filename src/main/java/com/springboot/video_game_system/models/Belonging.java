package com.springboot.video_game_system.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_BELONGING")
@AllArgsConstructor
@Getter @Setter @ToString
public class Belonging {

    @EmbeddedId
    private BelongingId belongingId= new BelongingId();

    @ManyToOne
    @MapsId(value = "gameId")
    @JoinColumn(name = "game_id",insertable = false,updatable = false)
    private Game game;

    @ManyToOne
    @MapsId(value = "gameListId")
    @JoinColumn(name = "game_list_id",insertable = false,updatable = false)
    private GameList gameList;

    private Integer position;

    public Belonging(){
    }

    public Belonging(Game game,GameList gameList,Integer position){
        this.game= game;
        this.gameList= gameList;
        this.position= position;
    }

}
