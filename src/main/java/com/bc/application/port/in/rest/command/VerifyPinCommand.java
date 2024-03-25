package com.bc.application.port.in.rest.command;

import com.bc.exception.CommonException;
import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import static com.bc.exception.ErrorConstants.*;
import static com.bc.utilities.Validator.*;

/**
 * Command object for Verify PIN request
 */
@Getter
@Slf4j
public class VerifyPinCommand {

    private final String pan;
    private final String pinVerificationKey;
    private final String pinLength;
    private final String pin;
    private final String pinOffset;

    /**
     * Constructor for builder to instantiate the Verify Pin Command object
     * @param builder Builder object.
     */
    private VerifyPinCommand(Builder builder){

        this.pan = builder.pan;
        this.pinVerificationKey = builder.pinVerificationKey;
        this.pinLength = builder.pinLength;
        this.pin = builder.pin;
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
                "pin: " + pin + ", " +
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
        PinVerificationKeySetter setPan(String pan);
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
        PinLengthSetter setPinVerificationKey(String pinVerificationKey);
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
        PinSetter setPinLength(String pinLength);
    }

    /**
     * Builder interface for setting Pin
     */
    public interface PinSetter{
        /**
         * Method for setting the PIN from request.
         * @param pin PIN to be verified.
         * @return FinshBuild interface.
         */
        PinOffsetSetter setPin(String pin);

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
        FinishBuild setPinOffset(String pinOffset);

    }

    /**
     * Builder interface for building the object
     */
    public interface FinishBuild{
        /**
         * Method for setting building a new VerifyPINCommand object instance.
         * @return VerifyPinCommand object.
         */
        VerifyPinCommand build();

    }

    /**
     * Builder for Verify Pin Command object
     */
    private static class Builder implements PanSetter, PinVerificationKeySetter, PinLengthSetter, PinOffsetSetter, PinSetter, FinishBuild {

        private String pan;
        private String pinVerificationKey;
        private String pinLength;
        private String pin;
        private String pinOffset;

        /**
         * Method for validating and setting the PAN from request.
         *
         * @param pan PAN.
         * @return Builder object with pan attribute set.
         */
        @Override
        public Builder setPan(String pan) {
            log.debug(this.getClass().getSimpleName() + " Pan received: {}.", pan);
            if (isPanValid(pan)) {
                this.pan = pan;
            } else {
                throw new CommonException(MSG_VER_PIN_CMD_PAN_NOT_NUM,
                        ERR_PINFNVR001,
                        Response.Status.BAD_REQUEST);
            }
            return this;
        }

        /**
         * Method for validating and setting the PIN Verification Key from request.
         *
         * @param pinVerificationKey PIN Verification Key.
         * @return Builder object with pinVerificationKey attribute set.
         */
        @Override
        public Builder setPinVerificationKey(String pinVerificationKey) {
            log.debug(this.getClass().getSimpleName() + " PinVerificationKey received: {}.", pinVerificationKey);
            if (isTdeaKeyValid(pinVerificationKey)) {
                this.pinVerificationKey = pinVerificationKey;
            } else {
                throw new CommonException(MSG_VER_PIN_CMD_PVK_INVALID,
                        ERR_PINFNVR002,
                        Response.Status.BAD_REQUEST);
            }
            return this;
        }

        /**
         * Method for validating and setting the PIN Length Key from request.
         *
         * @param pinLength Length of PIN.
         * @return Builder object with pinLength attribute set.
         */
        @Override
        public Builder setPinLength(String pinLength) {
            log.debug(this.getClass().getSimpleName() + " PinLength received: {}.", pinLength);
            if (isPinLengthValid(pinLength)) {
                this.pinLength = pinLength;
            } else {
                throw new CommonException(MSG_VER_PIN_CMD_PIN_LEN_INVALID,
                        ERR_PINFNVR003,
                        Response.Status.BAD_REQUEST);
            }
            return this;
        }

        /**
         * Method for setting the PIN from request.
         *
         * @param pin PIN to be verified.
         * @return FinshBuild interface.
         */
        @Override
        public PinOffsetSetter setPin(String pin) {
            log.debug(this.getClass().getSimpleName() + " Pin received: {}.", pin);
            if (isPinValid(pin)) {
                this.pin = pin;
            } else {
                throw new CommonException(MSG_VER_PIN_CMD_PIN_INVALID,
                        ERR_PINFNVR004,
                        Response.Status.BAD_REQUEST);
            }
            return this;
        }

        /**
         * Method for validating and setting the PIN Offset from request.
         *
         * @param pinOffset PIN Offset.
         * @return Builder object with pinOffset attribute set.
         */
        @Override
        public Builder setPinOffset(String pinOffset) {
            log.debug(this.getClass().getSimpleName() + " PinOffset received: {}.", pinOffset);
            if (isPinOffsetValid(pinLength, pinOffset)) {
                this.pinOffset = pinOffset;
            } else {
                throw new CommonException(MSG_VER_PIN_CMD_PIN_OFFSET_INVALID,
                        ERR_PINFNVR005,
                        Response.Status.BAD_REQUEST);
            }
            return this;
        }

        /**
         * Method for finalising the build of the Verify PIN Command object instance.
         *
         * @return Verify PIN Command object instance with all attribute set.
         */
        @Override
        public VerifyPinCommand build() {
            return new VerifyPinCommand(this);
        }

    }
}