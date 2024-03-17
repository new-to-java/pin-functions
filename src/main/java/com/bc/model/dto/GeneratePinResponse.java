package com.bc.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * This class defines the attributes for PIN Generation REST API request.
 */
@Getter
@Setter
@AllArgsConstructor
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

    @Override
    public String toString(){
        return this.getClass().getSimpleName() +
                " { " +
                "pin: " + pin + ", " +
                "pinLength: " + pinLength + ", " +
                "pinOffset: " + pinOffset +
                " }";
    }

}