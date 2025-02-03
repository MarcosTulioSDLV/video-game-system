package com.springboot.video_game_system.exceptions;

public class DestinationIndexOutOfBoundsException extends RuntimeException{

    public DestinationIndexOutOfBoundsException(){
    }

    public DestinationIndexOutOfBoundsException(String message){
        super(message);
    }

}
