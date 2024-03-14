package com.bc.application.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * PIN Functions core domain response class defining the attributes defining responses generated for various PIN functions
 * from the domain service.
 */
@Getter
@Setter
public class PinFunctionsResponse {

    String pin;
    String pinOffset;
    String pinLength;
    String intermediatePin;

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
                "intermediatePin: " + intermediatePin +
                " }";
    }

}