package com.zenltd.exception;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiError {
    private HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;
    private String message;
    private List<String> messages;

    public ApiError(Builder builder) {
        this.httpStatus = builder.httpStatus;
        this.createdAt = builder.createdAt;
        this.message = builder.message;
        this. messages = builder.messages;
    }

static class Builder {
        private HttpStatus httpStatus;
        private String message;
        private List<String> messages;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        private LocalDateTime createdAt;


        public Builder(){}

        public Builder withHttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }
        public Builder withCreatedAt() {
            this.createdAt = LocalDateTime.now();
            return this;
        }
        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }
         public Builder withMessages(List<String> messages) {
             this.messages = messages;
             return this;
        }

        public ApiError build(){
            return new ApiError(this);
        }

    }

}