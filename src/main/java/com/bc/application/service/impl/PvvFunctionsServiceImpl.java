package com.bc.application.service.impl;

import com.bc.application.domain.PvvFunctionsRequest;
import com.bc.application.domain.PvvFunctionsResponse;
import com.bc.application.port.in.rest.command.GeneratePvvCommand;
import com.bc.application.port.in.rest.command.VerifyPvvCommand;
import com.bc.application.port.in.rest.mapper.PvvFunctionsMapper;
import com.bc.application.service.PvvFunctionsService;
import com.bc.application.service.actions.GenerateVisaPvv;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class PvvFunctionsServiceImpl implements PvvFunctionsService {

    @Inject
    PvvFunctionsMapper pvvFunctionsMapper;

    /**
     * Method for generating a Visa Pin Verification Value from PIN
     *
     * @param generatePvvCommand Command object for PVV generation.
     * @return Generic PVV Functions response object.
     */
    @Override
    public PvvFunctionsResponse generatePvv(GeneratePvvCommand generatePvvCommand) {
        // Map command to domain request
        log.debug("Calling mapper for Generate PVV Command to PvvFunction Request domain.");
        PvvFunctionsRequest pvvFunctionsRequest = pvvFunctionsMapper.mapGeneratePvvCommandToDomainRequest(generatePvvCommand);
        log.debug("PVV Functions mapped domain object: {}.", pvvFunctionsRequest);
        // Builder pattern to generate PVV
        GenerateVisaPvv generateVisaPvv = GenerateVisaPvv
                .build()
                .buildTransformedSecurityParameter(pvvFunctionsRequest.getPan(),
                        pvvFunctionsRequest.getPvvKeyIndex(),
                        pvvFunctionsRequest.getPin()
                )
                .encryptTspWithPvvKey(pvvFunctionsRequest.getPvvKey())
                .getPinVerificationValue()
                .build();
        // Map Generate Visa PVV response to domain response
        PvvFunctionsResponse generatePvvResponse = pvvFunctionsMapper.mapGeneratePvvActionDomainResponse(generateVisaPvv);
        log.debug("Generate Visa PVV action response: {}.", generatePvvResponse);

        return generatePvvResponse;
    }

    /**
     * Method for verifying a Visa PVV
     *
     * @param verifyPvvCommand Command object for PVV verification.
     * @return Generic PVV Functions response object.
     */
    @Override
    public PvvFunctionsResponse verifyPvv(VerifyPvvCommand verifyPvvCommand) {
        return null;
    }
}