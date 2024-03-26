package com.bc.application.service.impl;

import com.bc.application.domain.PvvFunctionsRequest;
import com.bc.application.domain.PvvFunctionsResponse;
import com.bc.application.port.in.rest.command.GeneratePvvCommand;
import com.bc.application.port.in.rest.command.VerifyPvvCommand;
import com.bc.application.port.in.rest.mapper.PvvFunctionsMapper;
import com.bc.application.service.PvvFunctionsService;
import com.bc.application.service.actions.GenerateVisaPvv;
import com.bc.application.service.actions.VerifyVisaPvv;
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
        PvvFunctionsRequest generatePvvRequest = pvvFunctionsMapper
                .mapGeneratePvvCommandToDomainRequest(generatePvvCommand);
        log.debug("Mapped Visa PVV generation object : {}.", generatePvvRequest);
        // Builder pattern to generate PVV
        GenerateVisaPvv generatedVisaPvv = generateVisaPvv(generatePvvRequest);
        // Map Generate Visa PVV response to domain response
        PvvFunctionsResponse generatePvvResponse = pvvFunctionsMapper
                .mapGeneratePvvActionDomainResponse(generatedVisaPvv);
        log.debug("Mapped Visa PVV generation response object: {}.", generatePvvResponse);

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

        // Map command to domain request
        PvvFunctionsRequest verifyVisaPvvRequest = pvvFunctionsMapper
                .mapVerifyPvvCommandToDomainRequest(verifyPvvCommand);
        log.debug("Mapped Visa PVV verification object : {}.", verifyVisaPvvRequest);
        VerifyVisaPvv verifiedVisaPvv = verifyVisaPvv(verifyVisaPvvRequest);
        // Map Verify Visa PVV response to domain response
        PvvFunctionsResponse verifyPvvResponse = pvvFunctionsMapper
                .mapVerifyPvvActionDomainResponse(verifiedVisaPvv);
        // Log
        log.debug("Mapped Visa PVV verification response object: {}.", verifyPvvResponse);
        // Return
        return verifyPvvResponse;
    }

    /**
     * Method for generating a Visa PVV for an input PIN.
     *
     * @param generatePvv Request object containing attributes for PVV generation.
     * @return A 4 digit Visa PVV for the input PIN.
     */
    private GenerateVisaPvv generateVisaPvv(PvvFunctionsRequest generatePvv){
        // Builder pattern to generate PVV
        GenerateVisaPvv generateVisaPvv = GenerateVisaPvv
                .build()
                .buildTransformedSecurityParameter(generatePvv.getPan(),
                        generatePvv.getPvvKeyIndex(),
                        generatePvv.getPin()
                )
                .encryptTspWithPvvKey(generatePvv.getPvvKey())
                .getPinVerificationValue()
                .build();
        // Log
        log.debug("Visa PVV generation object: {}.", generateVisaPvv);
        // Return
        return generateVisaPvv;
    }

    /**
     * Method for generating and verifying Visa PVV for an input PIN.
     *
     * @param verifyVisaPvvRequest Request object containing attributes for PVV verification.
     * @return PVV verification object containing the PVV verification response.
     */
    private VerifyVisaPvv verifyVisaPvv(PvvFunctionsRequest verifyVisaPvvRequest){
        // Generate PVV
        GenerateVisaPvv generatedVisaPvv = GenerateVisaPvv.build()
                .buildTransformedSecurityParameter(verifyVisaPvvRequest.getPan(),
                        verifyVisaPvvRequest.getPvvKeyIndex(),
                        verifyVisaPvvRequest.getPin())
                .encryptTspWithPvvKey(verifyVisaPvvRequest.getPvvKey())
                .getPinVerificationValue()
                .build();
        // Verify PVV
        VerifyVisaPvv verifiedVisaPvv = VerifyVisaPvv.build()
                .setPvv(verifyVisaPvvRequest.getPvv())
                .setGeneratedPvv(generatedVisaPvv.getPvv())
                .verifyPvv()
                .build();
        // Log
        log.debug("Visa PVV verification object: {}.", verifiedVisaPvv);
        // Return
        return verifiedVisaPvv;

    }

}