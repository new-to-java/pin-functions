package com.bc.adapter.in.rest.mapper;

import com.bc.application.domain.PinFunctionsResponse;
import com.bc.model.dto.GeneratePinResponse;
import org.mapstruct.Mapper;

/**
 * Mapper interface for mapping PinFunctionsResponse domain object to Generate PIN Response.
 */
@Mapper(componentModel = "cdi")
public interface PinFunctionsResponseMapper {
    /**
     * Mapper method for mapping PinFunctionsResponse core domain response object to Generate PIN Response DTO.
     * @param pinFunctionsResponse GeneratePinCommand core domain response object.
     * @return PinFunctionsRequest DTO object.
     */
    GeneratePinResponse mapPinFunctionResponseToGeneratePinResponseDto(PinFunctionsResponse pinFunctionsResponse);

}