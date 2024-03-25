package com.bc.application.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PIN Functions core domain response class defining the attributes generated for various PIN functions
 * from the domain service.
 */
@Getter
@Setter
@NoArgsConstructor
public class PinFunctionsResponse {

    private String pin;
    private String pinOffset;
    private String pinLength;
    private String intermediatePin;
    private String calculatedPin;
    private boolean pinVerified;

    /**
     * Override default toString method to generate a string representation of class attributes for logging.
     * @return String representation of class attributes.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                " { " +
                "pin: " + pin + ", " +
                "pinLength: " + pinLength + ", " +
                "pinOffset: " + pinOffset + ", " +
                "intermediatePin: " + intermediatePin + ", " +
                "calculatedPin: " + calculatedPin + ", " +
                "pinVerified: " + pinVerified +
                " }";
    }

}