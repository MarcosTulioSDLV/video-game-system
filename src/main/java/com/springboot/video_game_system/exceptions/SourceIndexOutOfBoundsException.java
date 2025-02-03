package com.springboot.video_game_system.exceptions;

public class SourceIndexOutOfBoundsException extends RuntimeException{

    public SourceIndexOutOfBoundsException(){
    }

    public SourceIndexOutOfBoundsException(String message){
        super(message);
    }

}
