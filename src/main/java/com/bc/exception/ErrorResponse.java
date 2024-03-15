package com.bc.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.Setter;

/**
 * Class defining attributes and methods for error response handling.
 */

@Getter
@Setter
public class ErrorResponse {

    @JsonProperty("Status:")
    Response.Status httpStatusCode;
    @JsonProperty("ErrorCode:")
    private String errorCode;
    @JsonProperty("ErrorMessage:")
    private String errorMessage;

}