package com.bc.application.port.in.rest.mapper;

import com.bc.application.domain.PvvFunctionsRequest;
import com.bc.application.domain.PvvFunctionsResponse;
import com.bc.application.port.in.rest.command.GeneratePvvCommand;
import com.bc.application.service.actions.GenerateVisaPvv;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface PvvFunctionsMapper {
    /**
     * Mapper method for mapping GeneratePvvCommand object to PvvFunctions core domain request object.
     * @param generatePvvCommand GeneratePvvCommand object.
     * @return PvvFunctionsRequest domain object.
     */
    PvvFunctionsRequest mapGeneratePvvCommandToDomainRequest(GeneratePvvCommand generatePvvCommand);

    /**
     * Mapper method for mapping GenerateVisaPvv action object to PvvFunctions core domain response object.
     * @param generateVisaPvv GenerateVisaPvv action object.
     * @return PvvFunctionsResponse domain object.
     */
    PvvFunctionsResponse mapGeneratePvvActionDomainResponse(GenerateVisaPvv generateVisaPvv);

}