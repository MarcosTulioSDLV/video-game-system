package com.springboot.video_game_system.exceptions;


public class BelongingExistsException extends RuntimeException{

    public BelongingExistsException(){
    }

    public BelongingExistsException(String message){
        super(message);
    }

}
