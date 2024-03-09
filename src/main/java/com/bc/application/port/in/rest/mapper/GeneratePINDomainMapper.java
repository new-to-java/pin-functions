package com.bc.application.port.in.rest.mapper;

import com.bc.application.domain.PinFunctions;
import com.bc.model.dto.GeneratePINRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for mapping Generate PIN request to PinFunctions Domain object.
 */
@Mapper(componentModel = "cdi")
public interface GeneratePINDomainMapper {
    @Mapping(target = "pinFunctionRequested", constant = "PIN_GENERATE")
    PinFunctions mapToDomain(GeneratePINRequest generatePINRequest);

}