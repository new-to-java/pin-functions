package com.bc.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * This class defines the attributes for PIN verification REST API request.
 */
@Getter
@NoArgsConstructor
@Schema(name = "Verify PIN Request", description = "PIN Verification request DTO Class.")
public class VerifyPinRequest {
    @JsonProperty("Pan")
    @Schema(example = "4123456789012345")
    String pan;
    @JsonProperty("PinVerificationKey")
    @Schema(example = "5050505050505050")
    String pinVerificationKey;
    @JsonProperty("Pin")
    @Schema(example = "261895353819")
    String pin;
    @JsonProperty("PinLength")
    @Schema(example = "12")
    String pinLength;
    @JsonProperty("PinOffset")
    @Schema(example = "111111111111")
    String pinOffset;

    /**
     * Override default toString method to generate a string representation of class attributes for logging.
     * @return String representation of class attributes.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                " { " +
                "pan: " + pan + ", " +
                "pinVerificationKey: " + pinVerificationKey + ", " +
                "pin: " + pin + ", " +
                "pinLength: " + pinLength + ", " +
                "pinOffset: " + pinOffset +
                " }";
    }
}