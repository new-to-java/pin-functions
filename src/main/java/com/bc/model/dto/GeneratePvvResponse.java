package com.bc.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * This class defines the attributes for PVV Generation REST API request.
 */
@Getter
@Setter
@AllArgsConstructor
public class GeneratePvvResponse {
    @JsonProperty("Pvv")
    @Schema(example = "1234")
    String pvv;
    @JsonProperty("Pin")
    @Schema(example = "1111")
    String pin;

    @Override
    public String toString(){
        return this.getClass().getSimpleName() +
                " { " +
                "pvv: " + pvv + ", " +
                "pin: " + pin +
                " }";
    }

}