package com.bc.application.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * PVV Functions core domain class defining the attributes required for making requests to various PVV functions
 * from the domain service.
 */
@Getter
@Setter
public class PvvFunctionsRequest {

    private String pan;
    private String pin;
    private String pvvKeyIndex;
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