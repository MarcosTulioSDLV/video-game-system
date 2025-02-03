package com.springboot.video_game_system.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_GAME_LIST")
@AllArgsConstructor
@Getter @Setter @ToString(exclude = "belongingList")
//@EqualsAndHashCode("belongingList")
public class GameList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "gameList",cascade = CascadeType.REMOVE)
    private List<Belonging> belongingList= new ArrayList<>();

    public GameList(){
    }

}
