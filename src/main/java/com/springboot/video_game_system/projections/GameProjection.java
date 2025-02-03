package com.springboot.video_game_system.projections;

public interface GameProjection {

    Long getId();
    String getTitle();
    Integer getReleaseYear();
    String getImgUrl();
    String getShortDescription();
    Integer getPosition();

}
