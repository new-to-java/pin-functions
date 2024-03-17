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
public class GeneratePinResponse {
    @JsonProperty("Pin")
    @Schema(example = "123456789012")
    String pin;
    @JsonProperty("PinOffset")
    @Schema(example = "111111111111")
    String pinOffset;
    @JsonProperty("PinLength")
    @Schema(example = "12")
    String pinLength;
    @JsonProperty("IntermediatePin")
    @Schema(example = "012345678901")
    String intermediatePin;

    public GeneratePinResponse(String pin, String pinLength, String pinOffset, String intermediatePin){
        this.pin = pin;
        this.pinLength = pinLength;
        this.pinOffset = pinOffset;
        this.intermediatePin = intermediatePin;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName() +
                " { " +
                "pin: " + pin + ", " +
                "pinLength: " + pinLength + ", " +
                "pinOffset: " + pinOffset +
                "intermediatePin: " + intermediatePin +
                " }";
    }

}