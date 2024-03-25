package com.bc.adapter.in.rest.mapper;

import com.bc.application.domain.PinFunctionsResponse;
import com.bc.model.dto.GeneratePinResponse;
import com.bc.model.dto.VerifyPinResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Mapper interface for mapping PinFunctionsResponse domain object to Generate PIN Response.
 */
@Mapper(componentModel = "cdi")
public interface PinFunctionsResponseMapper {
    /**
     * Mapper method for mapping PinFunctionsResponse core domain response object to Generate PIN Response DTO.
     * @param pinFunctionsResponse Pin Functions core domain response object.
     * @return PinFunctionsRequest DTO object.
     */
    GeneratePinResponse mapPinFunctionResponseToGeneratePinResponseDto(PinFunctionsResponse pinFunctionsResponse);

    /**
     * Mapper method for mapping PinFunctionsResponse core domain response object to Verify PIN Response DTO.
     * @param pinFunctionsResponse Pin Functions core domain response object.
     * @return PinFunctionsRequest DTO object.
     */
    @Mappings({
            @Mapping(source = "calculatedPin", target = "generatedPin")
    })
    VerifyPinResponse mapPinFunctionResponseToVerifyPinResponseDto(PinFunctionsResponse pinFunctionsResponse);

}