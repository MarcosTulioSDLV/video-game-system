package com.springboot.video_game_system.exceptions;

public class GameTitleExistsException extends RuntimeException{

    public GameTitleExistsException(){
    }

    public GameTitleExistsException(String message){
        super(message);
    }

}
