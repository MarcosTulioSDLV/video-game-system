package com.springboot.video_game_system.exceptions;

public class GameNotFoundException extends RuntimeException{

    public GameNotFoundException(){
    }

    public GameNotFoundException(String message){
        super(message);
    }
}
