package com.bc.application.service.impl;

import com.bc.application.domain.PinFunctionsRequest;
import com.bc.application.domain.PinFunctionsResponse;
import com.bc.application.port.in.rest.command.DecryptPinCommand;
import com.bc.application.port.in.rest.command.EncryptPinCommand;
import com.bc.application.port.in.rest.command.GeneratePinCommand;
import com.bc.application.port.in.rest.command.VerifyPinCommand;
import com.bc.application.port.in.rest.mapper.PinFunctionsMapper;
import com.bc.application.service.PinFunctionsService;
import com.bc.application.service.actions.GenerateIBM3624Pin;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class PinFunctionsServiceImpl implements PinFunctionsService {

    @Inject
    PinFunctionsMapper pinFunctionsMapper;

    /**
     * Method for generating an IBM 3624 ATM Offset based PIN
     *
     * @param generatePinCommand Command object for PIN generation.
     * @return Generic PIN Functions response object.
     */
    @Override
    public PinFunctionsResponse generatePin(GeneratePinCommand generatePinCommand) {

        PinFunctionsRequest generatePin = pinFunctionsMapper.mapCommandToDomain(generatePinCommand);
        GenerateIBM3624Pin ibm3624Pin = GenerateIBM3624Pin.builder()
                .getPinValidationData(generatePinCommand.getPan())
                .encryptPinValidationData("0123456789ABCDEFFEDCBA9876543210")
                .decimaliseEncryptedValidationData("0123456789012345")
                .truncateToPinLength(4)
                .addPinOffset("0859")
                .build();

        log.debug("IBM 3624 PIN Generation response: {}.", ibm3624Pin);

        return null;
    }

    /**
     * Method for verifying an IBM 3624 ATM Offset based PIN
     *
     * @param verifyPinCommand Command object for PIN verification.
     * @return Generic PIN Functions response object.
     */
    @Override
    public PinFunctionsResponse verifyPin(VerifyPinCommand verifyPinCommand) {
        return null;
    }

    /**
     * Method for encrypting a plain text PIN as per a requested PIN block format
     *
     * @param encryptPinCommand Command object for PIN encryption.
     * @return Generic PIN Functions response object.
     */
    @Override
    public PinFunctionsResponse encryptPin(EncryptPinCommand encryptPinCommand) {
        return null;
    }

    /**
     * Method for decrypting a PIN block and extracting clear PIN
     *
     * @param decryptPinCommand Command object for PIN encryption.
     * @return Generic PIN Functions response object.
     */
    @Override
    public PinFunctionsResponse decryptPin(DecryptPinCommand decryptPinCommand) {
        return null;
    }
}