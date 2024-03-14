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
     * Mapper method for mapping PINFunctionsResponse core domain response object to Generate PIN Response.
     * @param pinFunctionsResponse GeneratePinCommand object.
     * @return PinFunctionsRequest domain object.
     */
    GeneratePinResponse mapPinFunctionResponseToGeneratePinResponseDto(PinFunctionsResponse pinFunctionsResponse);

}
