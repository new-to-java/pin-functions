package com.bc.adapter.in.rest;

import com.bc.adapter.in.rest.mapper.PinFunctionsResponseMapper;
import com.bc.application.domain.PinFunctionsResponse;
import com.bc.application.port.in.rest.client.PinFunctions;
import com.bc.application.port.in.rest.command.GeneratePinCommand;
import com.bc.application.port.in.rest.mapper.PinFunctionsRequestMapper;
import com.bc.application.service.impl.PinFunctionsServiceImpl;
import com.bc.model.dto.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class PinFunctionsImpl implements PinFunctions {

    @Inject
    PinFunctionsResponseMapper pinFunctionsResponseMapper;

    @Inject
    PinFunctionsServiceImpl pinFunctionsService;

    /**
     * Rest API endpoint for generating a clear text PIN.
     *
     * @param generatePinRequest PIN generation request.
     * @return PIN generation response.
     */
    @Override
    public Response generatePin(GeneratePinRequest generatePinRequest) {

        log.debug("Generate PIN Request received: {}.", generatePinRequest.toString());
        // Build command object from DTO
        GeneratePinCommand generatePinCommand = GeneratePinCommand.builder()
                .pan(generatePinRequest.getPan())
                .pinVerificationKey(generatePinRequest.getPinVerificationKey())
                .pinLength(generatePinRequest.getPinLength())
                .pinOffset(generatePinRequest.getPinOffset())
                .build();
        // Call service to generate PIN and map response to Dto
        PinFunctionsResponse pinFunctionsResponse = pinFunctionsService.generatePin(generatePinCommand);
        GeneratePinResponse generatePinResponse = pinFunctionsResponseMapper
                .mapPinFunctionResponseToGeneratePinResponseDto(pinFunctionsResponse);
        // Build REST API response
        return Response.ok().entity(generatePinResponse).build();
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
        return null;
    }

    /**
     * Rest API endpoint for generating PVV.
     *
     * @param generatePVVRequest PVV generation request.
     * @return PVV generation response.
     */
    @Override
    public Response generatePvv(GeneratePvvRequest generatePVVRequest) {
        return null;
    }

    /**
     * Rest API endpoint for verifying PVV.
     *
     * @param verifyPVVRequest PVV verification request.
     * @return PVV verification response.
     */
    @Override
    public Response verifyPvv(VerifyPvvRequest verifyPVVRequest) {
        return null;
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
}
