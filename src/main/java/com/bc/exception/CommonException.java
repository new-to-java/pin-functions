package com.bc.exception;

import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.Setter;

/**
 * Class extending the java Exception class to handle exceptions gracefully.
 */
@Getter
@Setter
public class CommonException extends RuntimeException {

    private Response.Status httpStatusCode;
    private String errorCode;

    public CommonException(String errorMessage, String errorCode, Response.Status httpStatusCode){
        super(errorMessage);
        this.errorCode = errorCode;
        this.httpStatusCode = httpStatusCode;

    }

}