package com.bc.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Class defining attributes and methods for error response handling.
 */

@Getter
@Setter
public class ErrorResponse {

    @JsonProperty("Status:")
    @Schema(example = "HttpStatusCode")
    Response.Status httpStatusCode;
    @JsonProperty("ErrorCode:")
    private String errorCode;
    @JsonProperty("ErrorMessage:")
    private String errorMessage;

}