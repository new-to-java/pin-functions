package com.bc.application.port.in.rest.mapper;

import com.bc.application.domain.PinFunctionsRequest;
import com.bc.application.domain.PinFunctionsResponse;
import com.bc.application.port.in.rest.command.GeneratePinCommand;
import com.bc.model.dto.GeneratePinResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for mapping Generate PIN request to PinFunctionsRequest Domain object.
 */
@Mapper(componentModel = "cdi")
public interface PinFunctionsMapper {
    /**
     * Mapper method for mapping GeneratePinCommand object to PinFunctions core domain object.
     * @param generatePinCommand GeneratePinCommand object.
     * @return PinFunctions domain object.
     */
    @Mapping(target = "pinFunctionRequested", constant = "PIN_GENERATE")
    PinFunctionsRequest mapCommandToDomain(GeneratePinCommand generatePinCommand);

    /**
     * Mapper method for mapping PinFunctions core domain object to GeneratePinResponse DTO.
     * @param pinFunctionsResponse PinFunctionsResponse core domain object.
     * @return GeneratePinResponse DTO object.
     */
    GeneratePinResponse mapDomainToResponseDto(PinFunctionsResponse pinFunctionsResponse);

}