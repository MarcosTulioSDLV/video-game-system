package com.springboot.video_game_system.exceptions;

public class GameListNameExistsException extends RuntimeException{

    public GameListNameExistsException(){
    }

    public GameListNameExistsException(String message){
        super(message);
    }

}
