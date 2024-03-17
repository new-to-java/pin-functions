package com.bc.application.service;

import com.bc.application.domain.PvvFunctionsResponse;
import com.bc.application.port.in.rest.command.GeneratePvvCommand;
import com.bc.application.port.in.rest.command.VerifyPvvCommand;

/**
 * Pvv Functions Service interface defining methods to be supported by the core domain service Pvv Functions.
 */
public interface PvvFunctionsService {

    /**
     * Method for generating a Visa Pin Verification Value from PIN
     * @param generatePvvCommand Command object for PVV generation.
     * @return Generic PVV Functions response object.
     */
    PvvFunctionsResponse generatePvv(GeneratePvvCommand generatePvvCommand);

    /**
     * Method for verifying a Visa PVV
     * @param verifyPvvCommand Command object for PVV verification.
     * @return Generic PVV Functions response object.
     */
    PvvFunctionsResponse verifyPvv(VerifyPvvCommand verifyPvvCommand);

}