package com.bc.application.port.in.rest.mapper;

import com.bc.application.domain.PinFunctionsRequest;
import com.bc.application.domain.PinFunctionsResponse;
import com.bc.application.port.in.rest.command.GeneratePinCommand;
import com.bc.application.port.in.rest.command.VerifyPinCommand;
import com.bc.application.service.actions.GenerateIBM3624Pin;
import com.bc.application.service.actions.VerifyIBM3624Pin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Mapper interface for mapping Generate PIN request to PinFunctionsRequest domain object.
 */
@Mapper(componentModel = "cdi")
public interface PinFunctionsMapper {
    /**
     * Mapper method for mapping GeneratePinCommand object to PinFunctions core domain request object.
     * @param generatePinCommand GeneratePinCommand object.
     * @return PinFunctionsRequest domain object.
     */
    @Mappings({
            @Mapping(target = "pin", ignore = true)
    })
    PinFunctionsRequest mapGeneratePinCommandToDomainRequest(GeneratePinCommand generatePinCommand);

    /**
     * Mapper method for mapping Generate IBM 3624 PIN utility object to PinFunctions core domain response object.
     * @param generateIBM3624Pin Generate IBM 3624 PIN utility object.
     * @return PinFunctionsResponse domain object.
     */
    @Mappings({
            @Mapping(target = "calculatedPin", ignore = true),
            @Mapping(target = "pinVerified", ignore = true)
    })
    PinFunctionsResponse mapGenerateIBM3624PinToDomainResponse(GenerateIBM3624Pin generateIBM3624Pin);

    /**
     * Mapper method for mapping VerifyPinCommand object to PinFunctions core domain request object.
     * @param verifyPinCommand VerifyPinCommand object.
     * @return PinFunctionsRequest domain object.
     */
    PinFunctionsRequest mapVerifyPinCommandToDomainRequest(VerifyPinCommand verifyPinCommand);


    /**
     * Mapper method for mapping Verify IBM 3624 PIN utility object to PinFunctions core domain response object.
     * @param verifyIBM3624Pin Verify IBM 3624 PIN utility object.
     * @return PinFunctionsResponse domain object.
     */
    @Mappings({
            @Mapping(source = "pinToVerify", target = "pin"),
            @Mapping(source = "generatedPin", target = "calculatedPin"),
            @Mapping(target = "pinOffset", ignore = true),
            @Mapping(target = "pinLength", ignore = true),
            @Mapping(target = "intermediatePin", ignore = true)
    })
    PinFunctionsResponse mapVerifyIBM3624PinToDomainResponse(VerifyIBM3624Pin verifyIBM3624Pin);

}