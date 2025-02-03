package com.springboot.video_game_system.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_GAME")
@AllArgsConstructor
@Getter @Setter @ToString(exclude = "belongingList")
//@EqualsAndHashCode(exclude = "belongingList")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String title;

    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String platforms;

    @Column(nullable = false)
    private Double score;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @Column(name = "short_description",columnDefinition = "TEXT", nullable = false)
    private String shortDescription;

    @Column(name = "long_description",columnDefinition = "TEXT", nullable = false)
    private String longDescription;

    @JsonIgnore
    @OneToMany(mappedBy = "game",cascade = CascadeType.REMOVE)
    private List<Belonging> belongingList= new ArrayList<>();

    public Game(){
    }

}
