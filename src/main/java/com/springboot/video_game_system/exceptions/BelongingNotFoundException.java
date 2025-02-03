package com.springboot.video_game_system.exceptions;

public class BelongingNotFoundException extends RuntimeException{

    public BelongingNotFoundException(){
    }

    public BelongingNotFoundException(String message){
        super(message);
    }

}
