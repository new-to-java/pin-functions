package com.bc.application.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * PVV Functions core domain response class defining the response attributes generated for various PVV functions.
 * from the domain service.
 */
@Getter
@Setter
public class PvvFunctionsResponse {

    String pvv;
    String pin;

    /**
     * Override default toString method to generate a string representation of class attributes for logging.
     * @return String representation of class attributes.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                " { " +
                "pvv: " + pvv + ", " +
                "pvv: " + pin + ", " +
                " }";
    }

}