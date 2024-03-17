package com.bc.application.port.in.rest.command;

import com.bc.exception.CommonException;
import com.bc.utilities.Validator;
import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import static com.bc.exception.ErrorConstants.*;

/**
 * Command object for Generate PIN request
 */
@Getter
@Slf4j
public class GeneratePinCommand {

    private final String pan;
    private final String pinVerificationKey;
    private final String pinLength;
    private final String pinOffset;

    /**
     * Constructor for builder to instantiate the Generate Pin Command object
     * @param builder Builder object.
     */
    private GeneratePinCommand(Builder builder){

        this.pan = builder.pan;
        this.pinVerificationKey = builder.pinVerificationKey;
        this.pinLength = builder.pinLength;
        this.pinOffset = builder.pinOffset;
        log.debug("Command object created: {}.", this);

    }

    /**
     * Override default toString method to generate a string representation of class attributes for logging.
     * @return String representation of class attributes.
     */
    @Override
    public String toString(){
        return this.getClass().getSimpleName() +
                " { " +
                "pan: " + pan + ", " +
                "pinVerificationKey: " + pinVerificationKey + ", " +
                "pinLength: " + pinLength + ", " +
                "pinOffset: " + pinOffset +
                " }";
    }

    /**
     * Public builder method for initiating sequential build
     * @return new Builder class instance.
     */
    public static PanSetter builder(){
        return new Builder();
    }

    /**
     * Builder interface for setting Pan
     */
    public interface PanSetter{
        /**
         * Method for setting the PAN from request.
         * @param pan PAN
         * @return PinVerificationKey setter interface.
         */
        PinVerificationKeySetter pan(String pan);
    }

    /**
     * Builder interface for setting PinVerificationKey
     */
    public interface PinVerificationKeySetter{
        /**
         * Method for setting the PIN Verification Key from request.
         * @param pinVerificationKey PIN Verification Key.
         * @return PinLength setter interface.
         */
        PinLengthSetter pinVerificationKey(String pinVerificationKey);
    }

    /**
     * Builder interface for setting PinLength
     */
    public interface PinLengthSetter{
        /**
         * Method for setting the PIN Length from request.
         * @param pinLength Length of the PIN.
         * @return PinOffset setter interface.
         */
        PinOffsetSetter pinLength(String pinLength);
    }

    /**
     * Builder interface for setting PinOffset
     */
    public interface PinOffsetSetter{
        /**
         * Method for setting the PIN Offset from request.
         * @param pinOffset PIN Offset to be added to PIN.
         * @return FinshBuild interface.
         */
        FinishBuild pinOffset(String pinOffset);

    }

    /**
     * Builder interface for building the object
     */
    public interface FinishBuild{
        /**
         * Method for setting building a new GeneratePINCommand object instance.
         * @return GeneratePinCommand object.
         */
        GeneratePinCommand build();

    }

    /**
     * Builder for Generate Pin Command object
     */
    private static class Builder implements PanSetter, PinVerificationKeySetter, PinLengthSetter, PinOffsetSetter, FinishBuild {

        private String pan;
        private String pinVerificationKey;
        private String pinLength;
        private String pinOffset;

        /**
         * Method for validating and setting the PAN from request.
         * @param pan PAN.
         * @return Builder object with pan attribute set.
         */
        @Override
        public Builder pan(String pan) {
            log.debug(this.getClass().getSimpleName() + " Pan received: {}.", pan);
            if (Validator.isPanValid(pan)) {
                this.pan = pan;
            } else {
                throw new CommonException(MSG_GEN_PIN_CMD_PAN_NOT_NUM,
                        ERR_PINFNGN001,
                        Response.Status.BAD_REQUEST);
            }
            return this;
        }

        /**
         * Method for validating and setting the PIN Verification Key from request.
         * @param pinVerificationKey PIN Verification Key.
         * @return Builder object with pinVerificationKey attribute set.
         */
        @Override
        public Builder pinVerificationKey(String pinVerificationKey) {
            log.debug(this.getClass().getSimpleName() + " PinVerificationKey received: {}.", pinVerificationKey);
            if (Validator.isTdeaKeyValid(pinVerificationKey)) {
                this.pinVerificationKey = pinVerificationKey;
            } else {
                throw new CommonException(MSG_GEN_PIN_CMD_PVK_INVALID,
                        ERR_PINFNGN002,
                        Response.Status.BAD_REQUEST);
            }
            return this;
        }

        /**
         * Method for validating and setting the PIN Length Key from request.
         * @param pinLength Length of PIN.
         * @return Builder object with pinLength attribute set.
         */
        @Override
        public Builder pinLength(String pinLength){
            log.debug(this.getClass().getSimpleName() + " PinLength received: {}.", pinLength);
            if (Validator.isPinLengthValid(pinLength)) {
                this.pinLength = pinLength;
            } else {
                throw new CommonException(MSG_GEN_PIN_CMD_PIN_LEN_INVALID,
                        ERR_PINFNGN003,
                        Response.Status.BAD_REQUEST);
            }
            return this;
        }

        /**
         * Method for validating and setting the PIN Offset from request.
         * @param pinOffset PIN Offset.
         * @return Builder object with pinOffset attribute set.
         */
        @Override
        public Builder pinOffset(String pinOffset){
            log.debug(this.getClass().getSimpleName() + " PinOffset received: {}.", pinOffset);
            if (Validator.isPinOffsetValid(pinLength, pinOffset)) {
                this.pinOffset = pinOffset;
            } else {
                throw new CommonException(MSG_GEN_PIN_CMD_PIN_OFFSET_INVALID,
                        ERR_PINFNGN004,
                        Response.Status.BAD_REQUEST);
            }
            return this;
        }

        /**
         * Method for finalising the build of the Generate PIN Command object instance.
         * @return Generate PIN Command object instance with all attribute set.
         */
        @Override
        public GeneratePinCommand build(){
            return new GeneratePinCommand(this);
        }

    }
}