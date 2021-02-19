package com.apsoft.conta.finance.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HttpError extends RuntimeException{
    private HttpStatus status;
    private String message;


    public static HttpError notFound(String message){
        return builder().status(HttpStatus.NOT_FOUND).message(message).build();
    }
}
