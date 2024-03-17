package com.bc.application.port.in.rest.client;

import com.bc.exception.ErrorResponse;
import com.bc.model.dto.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * Service interface class defining the various REST API endpoints that must be implemented.
 */
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/PinFunctions")
@Tag(name = "Pin Functions", description = "A collection of REST APIs that can be used to perform various PIN functions.")
public interface PinFunctions {
    /**
     * Rest API endpoint for generating a clear text PIN.
     * @param generatePINRequest PIN generation request.
     * @return PIN generation response.
     */
    @Path("/Generate/IBM3624Pin")
    @POST
    @Operation(
            operationId = "IBM3624Pin",
            summary = "Generate IBM 3624 PIN based on offset.",
            description = "This REST API allows the user to request generation of an IBM 3624 PIN " +
                    "for a given Pan, PinVerificationKey, PinLength and PinOffset supplied. " +
                    "The PIN length supplied must be a number in the range 4 to 12."
    )
    @APIResponse(
        responseCode = "201",
        description = "[Created] IBM 3624 PIN based on an offset has been successfully generated.",
        content = @Content(schema = @Schema(implementation = GeneratePinResponse.class))
    )
    @APIResponse(
            responseCode = "400",
            description = "[Bad Request] Request validation failure.",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @APIResponse(
            responseCode = "500",
            description = "[Internal Server Error] Server encountered an error while processing the request, if problem persists contact programmer.",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    Response generatePin(
            @RequestBody(
                    description = "IBM 3624 PIN based on offset generation request.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = GeneratePinRequest.class))
            )
            @Valid GeneratePinRequest generatePINRequest
    );

    /**
     * Rest API endpoint for verifying a PIN.
     * Note: The PIN maybe supplied as PIN block or clear text.
     * @param verifyPINRequest PIN verification request.
     * @return PIN verification response.
     */
    @Path("/VerifyPin")
    @POST
    Response verifyPin(@Valid VerifyPinRequest verifyPINRequest);

    /**
     * Rest API endpoint for generating PVV.
     * @param generatePVVRequest PVV generation request.
     * @return PVV generation response.
     */
    @Path("/GeneratePvv")
    @POST
    Response generatePvv(@Valid GeneratePvvRequest generatePVVRequest);

    /**
     * Rest API endpoint for verifying PVV.
     * @param verifyPVVRequest PVV verification request.
     * @return PVV verification response.
     */
    @Path("/VerifyPvv")
    @POST
    Response verifyPvv(@Valid VerifyPvvRequest verifyPVVRequest);

    /**
     * Rest API endpoint for encrypting a cleartext PIN and generating a PIN block.
     * @param encryptPin PIN encryption request.
     * @return PVV encryption response.
     */
    @Path("/EncryptPin")
    @POST
    Response encryptPin(@Valid EncryptPin encryptPin);

    /**
     * Rest API endpoint for decrypting a PIN block and extracting clear PIN along with PIN details.
     * @param decryptPin PIN decryption request.
     * @return PVV encryption response.
     */
    @Path("/DecryptPin")
    @POST
    Response decryptPin(@Valid DecryptPin decryptPin);

}