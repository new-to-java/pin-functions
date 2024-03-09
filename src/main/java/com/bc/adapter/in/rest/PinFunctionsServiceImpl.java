package com.bc.adapter.in.rest;

import com.bc.application.domain.PinFunctions;
import com.bc.application.port.in.rest.client.PinFunctionsService;
import com.bc.application.port.in.rest.mapper.GeneratePINDomainMapper;
import com.bc.model.dto.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class PinFunctionsServiceImpl implements PinFunctionsService {

    @Inject
    GeneratePINDomainMapper generatePINDomainMapper;

    /**
     * Rest API endpoint for generating a clear text PIN.
     *
     * @param generatePINRequest PIN generation request.
     * @return PIN generation response.
     */
    @Override
    public Response generatePin(GeneratePINRequest generatePINRequest) {
        PinFunctions pinFunctions = generatePINDomainMapper.mapToDomain(generatePINRequest);
        log.info("Mapped PIN Generation Request: {}.", pinFunctions);
        return null;
    }

    /**
     * Rest API endpoint for verifying a PIN.
     * Note: The PIN maybe supplied as PIN block or clear text.
     *
     * @param verifyPINRequest PIN verification request.
     * @return PIN verification response.
     */
    @Override
    public Response verifyPin(VerifyPINRequest verifyPINRequest) {
        return null;
    }

    /**
     * Rest API endpoint for generating PVV.
     *
     * @param generatePVVRequest PVV generation request.
     * @return PVV generation response.
     */
    @Override
    public Response generatePvv(GeneratePVVRequest generatePVVRequest) {
        return null;
    }

    /**
     * Rest API endpoint for verifying PVV.
     *
     * @param verifyPVVRequest PVV verification request.
     * @return PVV verification response.
     */
    @Override
    public Response verifyPvv(VerifyPVVRequest verifyPVVRequest) {
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
