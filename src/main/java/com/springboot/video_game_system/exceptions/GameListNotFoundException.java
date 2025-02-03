package com.springboot.video_game_system.exceptions;

public class GameListNotFoundException extends RuntimeException{

    public GameListNotFoundException(){
    }

    public GameListNotFoundException(String message){
        super(message);
    }

}
