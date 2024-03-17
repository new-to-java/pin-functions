package com.bc.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * This class defines the attributes for PIN Generation REST API request.
 */
@Getter
@Setter
@Slf4j
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

    public GeneratePinRequest(String pan, String pinVerificationKey, String pinLength, String pinOffset ){
        this.pan = pan;
        this.pinVerificationKey = pinVerificationKey;
        this.pinLength = pinLength;
        this.pinOffset = pinOffset;
    }

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