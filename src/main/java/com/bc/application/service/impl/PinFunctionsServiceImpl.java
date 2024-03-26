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
import com.bc.application.service.actions.VerifyIBM3624Pin;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Slf4j
public class PinFunctionsServiceImpl implements PinFunctionsService {

    @Inject
    @ConfigProperty(name = "APP.DECIMALISATION_TABLE")
    private String _DECIMALISATION_TABLE;

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
        // Map generate PIN command to PIN functions request domain object
        PinFunctionsRequest generatePinRequest = pinFunctionsMapper
                .mapGeneratePinCommandToDomainRequest(generatePinCommand);
        log.debug("Mapped PIN Generation object : {}.", generatePinRequest);
        // Generate IBM 3624 PIN
        GenerateIBM3624Pin ibm3624Pin = generateIBM3624Pin(generatePinRequest);
        // Map IBM 3624 PIN generation action to generate PIN functions response domain object
        PinFunctionsResponse generatePinResponse = pinFunctionsMapper
                .mapGenerateIBM3624PinToDomainResponse(ibm3624Pin);
        log.debug("Mapped PIN Generation response object: {}.", generatePinResponse);
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
        // Map verify PIN command to PIN functions request domain object
        PinFunctionsRequest pinVerificationRequest = pinFunctionsMapper
                .mapVerifyPinCommandToDomainRequest(verifyPinCommand);
        log.debug("Mapped PIN Verification object : {}.", pinVerificationRequest);
        // Verify IBM 3624 PIN
        VerifyIBM3624Pin verifyIBM3624Pin = verifyIBM3624Pin(pinVerificationRequest);
        // Map IBM 3624 PIN verification action to generate PIN functions response domain object
        PinFunctionsResponse pinVerificationResponse = pinFunctionsMapper
                .mapVerifyIBM3624PinToDomainResponse(verifyIBM3624Pin);
        log.debug("Mapped PIN Verification response object: {}.", pinVerificationResponse);
        return pinVerificationResponse;

    }

    /**
     * Method for generating an IBM 3624 PIN based on offset.
     * @param generatePinRequest Request object containing attributes for PIN generation.
     * @return An IBM 3624 Pin generation object containing the generated PIN.
     */
    private GenerateIBM3624Pin generateIBM3624Pin(PinFunctionsRequest generatePinRequest) {
        // Generate an IBM 3624 PIN based on offset
        GenerateIBM3624Pin generateIBM3624Pin = GenerateIBM3624Pin.builder()
                .getPinValidationDataFromPan(generatePinRequest.getPan())
                .encryptPinValidationData(generatePinRequest.getPinVerificationKey())
                .decimaliseEncryptedValidationData(getDecimalisationTable())
                .truncateToPinLength(generatePinRequest.getPinLength())
                .addPinOffset(generatePinRequest.getPinOffset())
                .build();
        // Log
        log.debug("IBM 3624 PIN generation object: {}.", generateIBM3624Pin);
        // Return
        return generateIBM3624Pin;
    }

    /**
     * Method for generating and verifying an IBM 3624 PIN based on offset against an input PIN.
     * Note: This method invokes the generatePin method first to generate the PIN and then compares the generated PIN
     * against the input pin for a match.
     *
     * @param pinVerificationRequest Request object containing attributes for PIN verification.
     * @return IBM 3624 Pin verification object containing the PIN verification response.
     */
    private VerifyIBM3624Pin verifyIBM3624Pin(PinFunctionsRequest pinVerificationRequest) {

        // Generate an IBM 3624 PIN based on offset
        GenerateIBM3624Pin generateIBM3624Pin = generateIBM3624Pin(pinVerificationRequest);
        // Verify an IBM 3624 PIN based on offset supplied against generated PIN and return.
        VerifyIBM3624Pin verifyIBM3624Pin = VerifyIBM3624Pin.builder()
                .getGeneratedPin(generateIBM3624Pin.getPin())
                .setPin(pinVerificationRequest.getPin())
                .verifyPin()
                .build();
        // Log
        log.debug("IBM 3624 PIN verification object: {}.", verifyIBM3624Pin);
        // Return
        return verifyIBM3624Pin;
    }

    /**
     * Method for checking if an external decimalisation table is supplied, if not use the default decimalisation table.
     *
     * @return Decimalisation table to be used in PIN generation.
     */
    private String getDecimalisationTable(){
        // Default decimalisation table
        final String DEFAULT_DECIMALISATION_TABLE = "0123456789012345";
        // If externally supplied decimalisation table is null, use default
        boolean externalTableSupplied = ((_DECIMALISATION_TABLE != null) && (!_DECIMALISATION_TABLE.isEmpty()));
        // Set the table to be used
        String decimalisationTable = (externalTableSupplied) ?
                _DECIMALISATION_TABLE :
                DEFAULT_DECIMALISATION_TABLE;
        // Log the table used
        log.debug(this.getClass().getSimpleName() + ": Decimalisation table used: \"{}\"." +
                        " Table is supplied externally: {}.",
                decimalisationTable,
                externalTableSupplied);
        return decimalisationTable;
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