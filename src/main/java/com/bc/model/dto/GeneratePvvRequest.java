package com.bc.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * This class defines the attributes for PVV Generation REST API request.
 */
@Getter
@NoArgsConstructor
public class GeneratePvvRequest {

    @JsonProperty("Pan")
    @Schema(example = "4123456789012345")
    private String pan;
    @JsonProperty("Pin")
    @Schema(example = "1234")
    private String pin;
    @JsonProperty("PvvKeyIndex")
    @Schema(example = "1")
    private String pvvKeyIndex;
    @JsonProperty("PvvKey")
    @Schema(example = "F0F0F0F0F0F0F0F0")
    private String pvvKey;

    /**
     * Override default toString method to generate a string representation of class attributes for logging.
     * @return String representation of class attributes.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                " { " +
                "pan: " + pan + ", " +
                "pin: " + pin + ", " +
                "pinKeyIndex: " + pvvKeyIndex + ", " +
                "pvvKey: " + pvvKey +
                " }";
    }

}