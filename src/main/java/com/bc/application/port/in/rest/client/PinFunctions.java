package com.bc.application.port.in.rest.client;

import com.bc.exception.ErrorResponse;
import com.bc.model.dto.*;
import jakarta.enterprise.context.ApplicationScoped;
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
            operationId = "GenrateIBM3624Pin",
            summary = "Generate an IBM 3624 PIN based on offset.",
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
            description = "[Bad Request] Request payload verification failed.",
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
            GeneratePinRequest generatePINRequest
    );

    /**
     * Rest API endpoint for verifying a PIN.
     * Note: The PIN maybe supplied as PIN block or clear text.
     * @param verifyPINRequest PIN verification request.
     * @return PIN verification response.
     */
    @Path("/Verify/IBM3624Pin")
    @POST
    @Operation(
            operationId = "VerifyIBM3624Pin",
            summary = "Verify an IBM 3624 PIN based on offset.",
            description = "This REST API allows the user to request verification of an IBM 3624 PIN supplied along with " +
                    "Pan, PinVerificationKey, PinLength and PinOffset supplied. " +
                    "The PIN length supplied must be a number in the range 4 to 12."
    )
    @APIResponse(
            responseCode = "200",
            description = "[OK] IBM 3624 PIN based on an offset has been successfully verified.",
            content = @Content(schema = @Schema(implementation = VerifyPinResponse.class))
    )
    @APIResponse(
            responseCode = "400",
            description = "[Bad Request] Request payload verification failed.",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @APIResponse(
            description = "[Not Acceptable] IBM 3624 PIN based on an offset has failed verification.",
            responseCode = "406",
            content = @Content(schema = @Schema(implementation = VerifyPinResponse.class))
    )
    @APIResponse(
            responseCode = "500",
            description = "[Internal Server Error] Server encountered an error while processing the request, if problem persists contact programmer.",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    Response verifyPin(VerifyPinRequest verifyPINRequest);

    /**
     * Rest API endpoint for generating PVV.
     * @param generatePVVRequest PVV generation request.
     * @return PVV generation response.
     */
    @Path("/Generate/VisaPvv")
    @POST
    @Operation(
            operationId = "GenerateVisaPvv",
            summary = "Generate a Visa PIN Verification Value (PVV).",
            description = "This REST API allows the user to request generation of a Visa PIN " +
                    "Verification Value (PVV) for a given Pan, Pin, PvvKeyIndex and PvvKey supplied. " +
                    "The PIN length supplied must be a number in the range 4 to 12."
    )
    @APIResponse(
            description = "[Created] Visa PIN Verification Value generated successfully.",
            responseCode = "201",
            content = @Content(schema = @Schema(implementation = GeneratePvvResponse.class))
    )
    @APIResponse(
            description = "[Bad Request] Request payload verification failed.",
            responseCode = "400",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @APIResponse(
            description = "[Internal Server Error] Server encountered an error while processing the request, if problem persists contact programmer.",
            responseCode = "500",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    Response generatePvv(@RequestBody(
            description = "Visa PIN Verification Value generation request.",
            required = true,
            content = @Content(schema = @Schema(implementation = GeneratePvvRequest.class))
    )
            GeneratePvvRequest generatePVVRequest
    );

    /**
     * Rest API endpoint for verifying PVV.
     * @param verifyPVVRequest PVV verification request.
     * @return PVV verification response.
     */
    @Path("/Verify/VisaPvv")
    @POST
    @Operation(
            operationId = "VerifyVisaPvv",
            summary = "Verify a Visa PIN Verification Value (PVV).",
            description = "This REST API allows the user to request verification of a Visa PIN Verification value" +
                    " supplied along with Pan, Pin, Pvv, PvvKeyIndex and PvvKey supplied. " +
                    "The PIN length supplied must be a number in the range 4 to 12 and PVV supplied must be a 4 digit number."
    )

    @APIResponse(
            description = "[OK] Visa PIN Verification Value (PVV) has been successfully verified.",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = VerifyPvvResponse.class))
    )
    @APIResponse(
            description = "[Bad Request] Request payload verification failed.",
            responseCode = "400",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @APIResponse(
            description = "[Not Acceptable] Visa PIN Verification Value (PVV) has failed verification.",
            responseCode = "406",
            content = @Content(schema = @Schema(implementation = VerifyPvvResponse.class))
    )
    @APIResponse(
            description = "[Internal Server Error] Server encountered an error while processing the request, if problem persists contact programmer.",
            responseCode = "500",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    Response verifyPvv(@RequestBody(
            description = "Visa PIN Verification Value verification request.",
            required = true,
            content = @Content(schema = @Schema(implementation = VerifyPvvRequest.class))
    )
                       VerifyPvvRequest verifyPVVRequest
    );

    /**
     * Rest API endpoint for encrypting a cleartext PIN and generating a PIN block.
     * @param encryptPin PIN encryption request.
     * @return PVV encryption response.
     */
    @Path("/Encrypt/Pin")
    @POST
    Response encryptPin(EncryptPin encryptPin);

    /**
     * Rest API endpoint for decrypting a PIN block and extracting clear PIN along with PIN details.
     * @param decryptPin PIN decryption request.
     * @return PVV encryption response.
     */
    @Path("/Decrypt/Pin")
    @POST
    Response decryptPin(DecryptPin decryptPin);

}