package com.bc.adapter.in.rest.mapper;

import com.bc.application.domain.PvvFunctionsResponse;
import com.bc.model.dto.GeneratePvvResponse;
import com.bc.model.dto.VerifyPvvResponse;
import org.mapstruct.Mapper;

/**
 * Mapper interface for mapping PvvFunctionsResponse domain object to Generate PVV Response.
 */
@Mapper(componentModel = "cdi")
public interface PvvFunctionsResponseMapper {
    /**
     * Mapper method for mapping PvvFunctionsResponse core domain response object to Generate PVV Response DTO.
     * @param pvvFunctionsResponse Pvv Functions core domain response object.
     * @return GeneratePVVResponse DTO object.
     */
    GeneratePvvResponse mapPvvFunctionResponseToGeneratePvvResponseDto(PvvFunctionsResponse pvvFunctionsResponse);

    /**
     * Mapper method for mapping PvvFunctionsResponse core domain response object to Generate PVV Response DTO.
     * @param pvvFunctionsResponse Pvv Functions core domain response object.
     * @return VerifyPVVResponse DTO object.
     */
    VerifyPvvResponse mapPvvFunctionResponseToVerifyPvvResponseDto(PvvFunctionsResponse pvvFunctionsResponse);

}