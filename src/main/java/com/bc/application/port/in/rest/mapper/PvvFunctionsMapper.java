package com.bc.application.port.in.rest.mapper;

import com.bc.application.domain.PvvFunctionsRequest;
import com.bc.application.domain.PvvFunctionsResponse;
import com.bc.application.port.in.rest.command.GeneratePvvCommand;
import com.bc.application.port.in.rest.command.VerifyPvvCommand;
import com.bc.application.service.actions.GenerateVisaPvv;
import com.bc.application.service.actions.VerifyVisaPvv;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "cdi")
public interface PvvFunctionsMapper {
    /**
     * Mapper method for mapping GeneratePvvCommand object to PvvFunctions core domain request object.
     * @param generatePvvCommand GeneratePvvCommand object.
     * @return PvvFunctionsRequest domain object.
     */
    @Mappings({
            @Mapping(target = "pvv", ignore = true)
    })
    PvvFunctionsRequest mapGeneratePvvCommandToDomainRequest(GeneratePvvCommand generatePvvCommand);

    /**
     * Mapper method for mapping GenerateVisaPvv action object to PvvFunctions core domain response object.
     * @param generatedVisaPvv GenerateVisaPvv action object.
     * @return PvvFunctionsResponse domain object.
     */

    @Mappings({
            @Mapping(target = "generatedPvv", ignore = true),
            @Mapping(target = "pvvVerified", ignore = true),
    })
    PvvFunctionsResponse mapGeneratePvvActionDomainResponse(GenerateVisaPvv generatedVisaPvv);

    /**
     * Mapper method for mapping VerifyPvvCommand object to PvvFunctions core domain request object.
     * @param verifyPvvCommand VerifyPvvCommand object.
     * @return PvvFunctionsRequest domain object.
     */
    PvvFunctionsRequest mapVerifyPvvCommandToDomainRequest(VerifyPvvCommand verifyPvvCommand);

    /**
     * Mapper method for mapping VerifyVisaPvv action object to PvvFunctions core domain response object.
     * @param verifiedVisaPvv VerifyVisaPvv action object.
     * @return PvvFunctionsResponse domain object.
     */
    @Mappings({
            @Mapping(target = "pin", ignore = true),
            @Mapping(source = "pvvToVerify", target = "pvv")
    })
    PvvFunctionsResponse mapVerifyPvvActionDomainResponse(VerifyVisaPvv verifiedVisaPvv);

}