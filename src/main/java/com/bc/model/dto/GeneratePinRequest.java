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
public class GeneratePinRequest {
    @JsonProperty("Pan")
    String pan;
    @JsonProperty("PVK")
    String pinVerificationKey;
    @JsonProperty("PinLength")
    String pinLength;
    @JsonProperty("PinOffset")
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