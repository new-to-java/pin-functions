package com.bc.application.port.in.rest.mapper;

import com.bc.application.domain.PinFunctionsRequest;
import com.bc.application.domain.PinFunctionsResponse;
import com.bc.application.port.in.rest.command.GeneratePinCommand;
import com.bc.application.service.actions.GenerateIBM3624Pin;
import org.mapstruct.Mapper;

/**
 * Mapper interface for mapping Generate PIN request to PinFunctionsRequest domain object.
 */
@Mapper(componentModel = "cdi")
public interface PinFunctionsRequestMapper {
    /**
     * Mapper method for mapping GeneratePinCommand object to PinFunctions core domain request object.
     * @param generatePinCommand GeneratePinCommand object.
     * @return PinFunctionsRequest domain object.
     */
    PinFunctionsRequest mapGeneratePinCommandToDomainRequest(GeneratePinCommand generatePinCommand);

    /**
     * Mapper method for mapping Generate IBM 3624 PIN utility object to PinFunctions core domain response object.
     * @param generateIBM3624Pin Generate IBM 3624 PIN utility object.
     * @return PinFunctionsResponse domain object.
     */
    PinFunctionsResponse mapGenerateIBM3624PinToDomainResponse(GenerateIBM3624Pin generateIBM3624Pin);

}