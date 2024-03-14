package com.bc.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * This class defines the attributes for PIN Generation REST API request.
 */
@Getter
@Setter
@Slf4j
public class GeneratePinResponse {
    @JsonProperty("Pin")
    String pin;
    @JsonProperty("PinOffset")
    String pinOffset;
    @JsonProperty("PinLength")
    String pinLength;
    @JsonProperty("IntermediatePin")
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