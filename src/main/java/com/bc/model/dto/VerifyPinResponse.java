package com.bc.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * This class defines the attributes for PIN Generation REST API request.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VerifyPinResponse {
    @JsonProperty("Pin")
    @Schema(example = "123456789012")
    String pin;
    @JsonProperty("GeneratedPin")
    @Schema(example = "111111111111")
    String generatedPin;
    @JsonProperty("VerificationSuccessful")
    @Schema(example = "true")
    String pinVerified;

    /**
     * Override default toString method to generate a string representation of class attributes for logging.
     * @return String representation of class attributes.
     */
    @Override
    public String toString(){
        return this.getClass().getSimpleName() +
                " { " +
                "pin: " + pin + ", " +
                "generatedPin: " + generatedPin + ", " +
                "pinVerified: " + pinVerified +
                " }";
    }

}