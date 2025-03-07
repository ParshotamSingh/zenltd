package com.zenltd.exception;

import java.util.List;

public class MethodArgumentNotValidException extends RuntimeException{
        private  String message;
        private List<String > messages;

        //**********Constructor**************
    public MethodArgumentNotValidException(String message){
            this.message = message;
        }
    public MethodArgumentNotValidException(List<String> messages){
        this.messages = messages;
    }

        //************getter setter**************
        @Override
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
    }

    public List<String> getMessages() {
        return messages;
    }
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
