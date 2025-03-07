package com.zenltd.exception;
import org.springframework.http.HttpStatus;
public class EntityNotFoundException extends RuntimeException {
    private  String message;

    //**********Constructor**************
    public EntityNotFoundException(String message){
        this.message = message;
    }

    //************ getter setter **************
    @Override
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}