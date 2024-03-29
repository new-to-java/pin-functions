package com.bc.adapter.in.rest;

import com.bc.adapter.in.rest.mapper.PinFunctionsResponseMapper;
import com.bc.adapter.in.rest.mapper.PvvFunctionsResponseMapper;
import com.bc.application.domain.PinFunctionsResponse;
import com.bc.application.domain.PvvFunctionsResponse;
import com.bc.application.port.in.rest.client.PinFunctions;
import com.bc.application.port.in.rest.command.GeneratePinCommand;
import com.bc.application.port.in.rest.command.GeneratePvvCommand;
import com.bc.application.port.in.rest.command.VerifyPinCommand;
import com.bc.application.port.in.rest.command.VerifyPvvCommand;
import com.bc.application.service.impl.PinFunctionsServiceImpl;
import com.bc.application.service.impl.PvvFunctionsServiceImpl;
import com.bc.model.dto.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class PinFunctionsImpl implements PinFunctions {

    @Inject
    PinFunctionsServiceImpl pinFunctionsService;

    @Inject
    PinFunctionsResponseMapper pinFunctionsResponseMapper;

    @Inject
    PvvFunctionsServiceImpl pvvFunctionsService;

    @Inject
    PvvFunctionsResponseMapper pvvFunctionsResponseMapper;

    /**
     * Rest API endpoint for generating a clear text PIN.
     *
     * @param generatePinRequest PIN generation request.
     * @return PIN generation response.
     */
    @Override
    public Response generatePin(GeneratePinRequest generatePinRequest) {

        log.debug("Generate PIN Request received: {}.", generatePinRequest.toString());
        // Build GeneratePINCommand object from Generate PIN Request DTO
        GeneratePinCommand generatePinCommand = GeneratePinCommand.builder()
                .setPan(generatePinRequest.getPan())
                .setPinVerificationKey(generatePinRequest.getPinVerificationKey())
                .setPinLength(generatePinRequest.getPinLength())
                .setPinOffset(generatePinRequest.getPinOffset())
                .build();
        log.debug("Mapped generate PIN command: {}.", generatePinCommand.toString());
        // Call service to generate PIN
        PinFunctionsResponse pinFunctionsResponse = pinFunctionsService
                .generatePin(generatePinCommand);
        // Map domain response object to Generate PIN Response DTO
        GeneratePinResponse generatePinResponse = pinFunctionsResponseMapper
                .mapPinFunctionResponseToGeneratePinResponseDto(pinFunctionsResponse);
        log.debug("Mapped generate PIN response DTO: {}.", generatePinResponse.toString());
        // Build REST API response
        return responseBuilder(
                generatePinResponse,
                Response.Status.CREATED
        );
    }

    /**
     * Rest API endpoint for verifying a PIN.
     * Note: The PIN maybe supplied as PIN block or clear text.
     *
     * @param verifyPINRequest PIN verification request.
     * @return PIN verification response.
     */
    @Override
    public Response verifyPin(VerifyPinRequest verifyPINRequest) {
        log.debug("Verify PIN Request received: {}.", verifyPINRequest.toString());
        VerifyPinCommand verifyPinCommand = VerifyPinCommand.builder()
                .setPan(verifyPINRequest.getPan())
                .setPinVerificationKey(verifyPINRequest.getPinVerificationKey())
                .setPinLength(verifyPINRequest.getPinLength())
                .setPin(verifyPINRequest.getPin())
                .setPinOffset(verifyPINRequest.getPinOffset())
                .build();
        log.debug("Mapped verify PIN command: {}.", verifyPinCommand.toString());
        // Call service to verify PIN
        PinFunctionsResponse pinFunctionsResponse = pinFunctionsService
                .verifyPin(verifyPinCommand);
        // Map domain response object to Verify PIN Response DTO
        VerifyPinResponse verifyPinResponse = pinFunctionsResponseMapper
                .mapPinFunctionResponseToVerifyPinResponseDto(pinFunctionsResponse);
        log.debug("Mapped verify PIN response DTO: {}.", verifyPinResponse.toString());
        // Alter HTTP status code based on PIN verification result
        Response.Status httpStatusCode = (pinFunctionsResponse.isPinVerified()) ?
                Response.Status.OK :
                Response.Status.NOT_ACCEPTABLE;
        // Build REST API response
        return responseBuilder(
                verifyPinResponse,
                httpStatusCode
        );
    }

    /**
     * Rest API endpoint for generating PVV.
     *
     * @param generatePVVRequest PVV generation request.
     * @return PVV generation response.
     */
    @Override
    public Response generatePvv(GeneratePvvRequest generatePVVRequest) {
        log.debug("Generate PVV Request received: {}.", generatePVVRequest.toString());
        // Build Generate PVV Command request object from Generate PVV Request DTO
        GeneratePvvCommand generatePvvCommand = GeneratePvvCommand.builder()
                .setPan(generatePVVRequest.getPan())
                .setPin(generatePVVRequest.getPin())
                .setPvvKeyIndex(generatePVVRequest.getPvvKeyIndex())
                .setPvvKey(generatePVVRequest.getPvvKey())
                .build();
        log.debug("Mapped generate PVV command: {}.", generatePvvCommand.toString());
        // Call service to generate PVV
        PvvFunctionsResponse pvvFunctionsResponse = pvvFunctionsService
                .generatePvv(generatePvvCommand);
        // Map domain response object to Generate PVV Response DTO
        GeneratePvvResponse generatePvvResponse = pvvFunctionsResponseMapper
                .mapPvvFunctionResponseToGeneratePvvResponseDto(pvvFunctionsResponse);
        log.debug("Mapped generate PVV response DTO: {}.", generatePvvResponse.toString());
        // Build REST API response
        return responseBuilder(
                generatePvvResponse,
                Response.Status.CREATED
        );
    }

    /**
     * Rest API endpoint for verifying PVV.
     *
     * @param verifyPVVRequest PVV verification request.
     * @return PVV verification response.
     */
    @Override
    public Response verifyPvv(VerifyPvvRequest verifyPVVRequest) {
        log.debug("Verify PVV Request received: {}.", verifyPVVRequest.toString());
        VerifyPvvCommand verifyPvvCommand = VerifyPvvCommand.builder()
                .setPan(verifyPVVRequest.getPan())
                .setPin(verifyPVVRequest.getPin())
                .setPvv(verifyPVVRequest.getPvv())
                .setPvvKeyIndex(verifyPVVRequest.getPvvKeyIndex())
                .setPvvKey(verifyPVVRequest.getPvvKey())
                .build();
        log.debug("Mapped verify PVV command: {}.", verifyPvvCommand.toString());
        // Call service to verify PVV
        PvvFunctionsResponse pvvFunctionsResponse = pvvFunctionsService
                .verifyPvv(verifyPvvCommand);
        // Map domain response object to Generate PVV Response DTO
        VerifyPvvResponse verifyPvvResponse = pvvFunctionsResponseMapper
                .mapPvvFunctionResponseToVerifyPvvResponseDto(pvvFunctionsResponse);
        log.debug("Mapped verify PVV response DTO: {}.", verifyPvvResponse.toString());
        // Alter HTTP status code based on PVV verification result
        Response.Status httpStatusCode = (pvvFunctionsResponse.isPvvVerified()) ?
                Response.Status.OK :
                Response.Status.NOT_ACCEPTABLE;
        // Build REST API response
        return responseBuilder(
                verifyPvvResponse,
                httpStatusCode
        );
    }

    /**
     * Rest API endpoint for encrypting a cleartext PIN and generating a PIN block.
     *
     * @param encryptPin PIN encryption request.
     * @return PVV encryption response.
     */
    @Override
    public Response encryptPin(EncryptPin encryptPin) {
        return null;
    }

    /**
     * Rest API endpoint for decrypting a PIN block and extracting clear PIN along with PIN details.
     *
     * @param decryptPin PIN decryption request.
     * @return PVV encryption response.
     */
    @Override
    public Response decryptPin(DecryptPin decryptPin) {
        return null;
    }

    /**
     * Generic method for handling JSON response object creation from response DTO class.
     *
     * @param <T>    Generic class identifying the entity class
     * @param entity DTO object to be converted to JSON
     * @return JSON response generated from entity object
     */
    private <T> Response responseBuilder(T entity, Response.Status httpStatusCode) {

        return Response
                .status(httpStatusCode)
                .entity(entity)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

}
