package com.bc.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * This class defines the attributes for PIN Generation REST API request.
 */
@Getter
@AllArgsConstructor
@Schema(name = "Generate PIN Request", description = "PIN Generation request DTO Class.")
public class GeneratePinRequest {
    @JsonProperty("Pan")
    @Schema(example = "4123456789012345")
    String pan;
    @JsonProperty("PinVerificationKey")
    @Schema(example = "5050505050505050")
    String pinVerificationKey;
    @JsonProperty("PinLength")
    @Schema(example = "12")
    String pinLength;
    @JsonProperty("PinOffset")
    @Schema(example = "111111111111")
    String pinOffset;

    @Override
    public String toString(){
        return this.getClass().getSimpleName() +
                " { " +
                "pan: " + pan + ", " +
                "pinVerificationKey: " + pinVerificationKey + ", " +
                "pinLength: " + pinLength + ", " +
                "pinOffset: " + pinOffset +
                " }";
    }

}