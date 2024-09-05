package com.example.redis.vo;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No Keys in redis")
public class NotFoundException extends RuntimeException{
    private String error;
    private HttpStatus httpStatus;

    public NotFoundException(String error, HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
    }
}
