package com.bc.application.service;

import com.bc.application.domain.PinFunctionsResponse;
import com.bc.application.port.in.rest.command.DecryptPinCommand;
import com.bc.application.port.in.rest.command.EncryptPinCommand;
import com.bc.application.port.in.rest.command.GeneratePinCommand;
import com.bc.application.port.in.rest.command.VerifyPinCommand;

/**
 * Pin Functions Service interface defining methods to be supported by the core domain service Pin Functions.
 */
public interface PinFunctionsService {

    /**
     * Method for generating an IBM 3624 ATM Offset based PIN
     * @param generatePinCommand Command object for PIN generation.
     * @return Generic PIN Functions response object.
     */
    public PinFunctionsResponse generatePin(GeneratePinCommand generatePinCommand);

    /**
     * Method for verifying an IBM 3624 ATM Offset based PIN
     * @param verifyPinCommand Command object for PIN verification.
     * @return Generic PIN Functions response object.
     */
    public PinFunctionsResponse verifyPin(VerifyPinCommand verifyPinCommand);

    /**
     * Method for encrypting a plain text PIN as per a requested PIN block format
     * @param encryptPinCommand Command object for PIN encryption.
     * @return Generic PIN Functions response object.
     */
    public PinFunctionsResponse encryptPin(EncryptPinCommand encryptPinCommand);

    /**
     * Method for decrypting a PIN block and extracting clear PIN
     * @param decryptPinCommand Command object for PIN encryption.
     * @return Generic PIN Functions response object.
     */
    public PinFunctionsResponse decryptPin(DecryptPinCommand decryptPinCommand);

}