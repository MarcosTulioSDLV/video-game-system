package com.springboot.video_game_system.infra;

import com.springboot.video_game_system.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    //For Game class
    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<Object> handleGameNotFoundException(GameNotFoundException e){
        return handleCustomException(e,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GameTitleExistsException.class)
    public ResponseEntity<Object> handleGameTitleExistsException(GameTitleExistsException e){
        return handleCustomException(e,HttpStatus.BAD_REQUEST);
    }

    //-----
    //For GameList class
    @ExceptionHandler(GameListNotFoundException.class)
    public ResponseEntity<Object> handleGameListNotFoundException(GameListNotFoundException e){
        return handleCustomException(e,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GameListNameExistsException.class)
    public ResponseEntity<Object> handleGameListNameExistsException(GameListNameExistsException e){
        return handleCustomException(e,HttpStatus.BAD_REQUEST);
    }

    //-----
    //For Belonging class
    @ExceptionHandler(BelongingNotFoundException.class)
    public ResponseEntity<Object> handleBelongingNotFoundException(BelongingNotFoundException e){
        return handleCustomException(e,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BelongingExistsException.class)
    public ResponseEntity<Object> handleBelongingExistsException(BelongingExistsException e){
        return handleCustomException(e,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SourceIndexOutOfBoundsException.class)
    public ResponseEntity<Object> handleSourceIndexOutOfBoundsException(SourceIndexOutOfBoundsException e){
        return handleCustomException(e,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DestinationIndexOutOfBoundsException.class)
    public ResponseEntity<Object> handleDestinationIndexOutOfBoundsException(DestinationIndexOutOfBoundsException e){
        return handleCustomException(e,HttpStatus.BAD_REQUEST);
    }

    //-----
    private ResponseEntity<Object> handleCustomException(RuntimeException e,HttpStatus httpStatus) {
        Map<String,Object> responseMessage= new LinkedHashMap<>();
        responseMessage.put("message",e.getMessage());
        responseMessage.put("status",httpStatus.value());
        return new ResponseEntity<>(responseMessage, httpStatus);
    }

}
