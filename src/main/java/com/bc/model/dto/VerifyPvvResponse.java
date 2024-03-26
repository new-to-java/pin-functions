package com.bc.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * This class defines the attributes for PVV Verification REST API response.
 */
@Getter
@Setter
@NoArgsConstructor
public class VerifyPvvResponse {

    @JsonProperty("Pvv")
    @Schema(example = "123456789012")
    String pvv;
    @JsonProperty("GeneratedPvv")
    @Schema(example = "123456789012")
    String generatedPvv;
    @JsonProperty("VerificationSuccessful")
    @Schema(example = "true")
    String pvvVerified;

    /**
     * Override default toString method to generate a string representation of class attributes for logging.
     * @return String representation of class attributes.
     */
    @Override
    public String toString(){
        return this.getClass().getSimpleName() +
                " { " +
                "pvv: " + pvv + ", " +
                "generatedPvv: " + generatedPvv + ", " +
                "pvvVerified: " + pvvVerified +
                " }";
    }

}