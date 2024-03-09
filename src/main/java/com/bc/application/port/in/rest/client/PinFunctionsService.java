package com.bc.application.port.in.rest.client;

import com.bc.model.dto.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Service interface class defining the various REST API endpoints that must be implemented.
 */
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/PinFunctions")
public interface PinFunctionsService {

    /**
     * Rest API endpoint for generating a clear text PIN.
     * @param generatePINRequest PIN generation request.
     * @return PIN generation response.
     */
    @Path("/GeneratePin")
    @POST
    Response generatePin(@Valid GeneratePINRequest generatePINRequest);

    /**
     * Rest API endpoint for verifying a PIN.
     * Note: The PIN maybe supplied as PIN block or clear text.
     * @param verifyPINRequest PIN verification request.
     * @return PIN verification response.
     */
    @Path("/VerifyPin")
    @POST
    Response verifyPin(@Valid VerifyPINRequest verifyPINRequest);

    /**
     * Rest API endpoint for generating PVV.
     * @param generatePVVRequest PVV generation request.
     * @return PVV generation response.
     */
    @Path("/GeneratePvv")
    @POST
    Response generatePvv(@Valid GeneratePVVRequest generatePVVRequest);

    /**
     * Rest API endpoint for verifying PVV.
     * @param verifyPVVRequest PVV verification request.
     * @return PVV verification response.
     */
    @Path("/VerifyPvv")
    @POST
    Response verifyPvv(@Valid VerifyPVVRequest verifyPVVRequest);

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