package com.bc.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * This class defines the attributes for PIN Generation REST API request.
 */
@Getter
@Setter
public class GeneratePINRequest {
    @JsonProperty("Pan")
    String pan;
    @JsonProperty("PVK")
    String pinVerificationKey;
    @JsonProperty("PinLength")
    String pinLength;
    @JsonProperty("PinOffset")
    String pinOffset;
}