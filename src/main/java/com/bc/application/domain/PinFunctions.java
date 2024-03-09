package com.bc.application.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PinFunctions {

    private String pan;
    private String pinVerificationKey;
    private String pinLength;
    private String pinOffset;
    private PinFunctionRequested pinFunctionRequested;

    /**
     * Override default toString method to generate a string representation of class attributes for logging.
     * @return String representation of class attributes.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" +
                "pan: " + pan + ", " +
                "pinVerificationKey: " + pinVerificationKey + ", " +
                "pinLength: " + pinLength + ", " +
                "pinOffset: " + pinOffset + ", " +
                "pinFunctionRequested: " + pinFunctionRequested +
                "}";
    }
}
