package com.bc.application.service.impl;

import com.bc.application.domain.PinFunctionsRequest;
import com.bc.application.domain.PinFunctionsResponse;
import com.bc.application.port.in.rest.command.DecryptPinCommand;
import com.bc.application.port.in.rest.command.EncryptPinCommand;
import com.bc.application.port.in.rest.command.GeneratePinCommand;
import com.bc.application.port.in.rest.command.VerifyPinCommand;
import com.bc.application.port.in.rest.mapper.PinFunctionsRequestMapper;
import com.bc.application.service.PinFunctionsService;
import com.bc.application.service.actions.GenerateIBM3624Pin;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class PinFunctionsServiceImpl implements PinFunctionsService {

    @Inject
    PinFunctionsRequestMapper pinFunctionsRequestMapper;

    /**
     * Method for generating an IBM 3624 ATM Offset based PIN
     *
     * @param generatePinCommand Command object for PIN generation.
     * @return Generic PIN Functions response object.
     */
    @Override
    public PinFunctionsResponse generatePin(GeneratePinCommand generatePinCommand) {

        String DECIMALISATION_TABLE = "0123456789012345";
        PinFunctionsRequest generatePinRequest = pinFunctionsRequestMapper.mapGeneratePinCommandToDomainRequest(generatePinCommand);

        log.debug("Mapped PIN Functions request object: {}.", generatePinRequest);

        GenerateIBM3624Pin ibm3624Pin = GenerateIBM3624Pin.builder()
                .getPinValidationDataFromPan(generatePinRequest.getPan())
                .encryptPinValidationData(generatePinRequest.getPinVerificationKey())
                .decimaliseEncryptedValidationData(DECIMALISATION_TABLE)
                .truncateToPinLength(generatePinRequest.getPinLength())
                .addPinOffset(generatePinRequest.getPinOffset())
                .build();

        log.debug("IBM 3624 PIN Generation response object: {}.", ibm3624Pin);

        PinFunctionsResponse generatePinResponse = pinFunctionsRequestMapper.mapGenerateIBM3624PinToDomainResponse(ibm3624Pin);

        log.debug("Mapped PIN Functions response object: {}.", generatePinResponse);

        return generatePinResponse;

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