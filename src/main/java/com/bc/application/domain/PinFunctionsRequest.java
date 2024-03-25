package com.bc.application.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * PIN Functions core domain class defining the attributes required for making requests to various PIN functions
 * from the domain service.
 */
@Getter
@Setter
public class PinFunctionsRequest {

    private String pan;
    private String pinVerificationKey;
    private String pinLength;
    private String pinOffset;
    private String pin;

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
                "pinLength: " + pinLength + ", " +
                "pinOffset: " + pinOffset + ", " +
                "pin: " + pin +
                " }";
    }
}