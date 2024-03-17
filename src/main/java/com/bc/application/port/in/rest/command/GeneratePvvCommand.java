package com.bc.application.port.in.rest.command;

import com.bc.exception.CommonException;
import com.bc.utilities.Validator;
import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import static com.bc.exception.ErrorConstants.*;

@Getter
@Slf4j
public class GeneratePvvCommand {

    private final String pan;
    private final String pin;
    private final String pvvKeyIndex;
    private final String pvvKey;

    /**
     * Private constructor to be used by the builder class to instantiate the Visa Pin Verification Value
     * generation command object.
     * @param builder Builder object containing the verified attributes for instantiating Visa PVV command object
     */
    private GeneratePvvCommand(Builder builder){
        this.pan = builder.pan;
        this.pin = builder.pin;
        this.pvvKeyIndex = builder.pvvKeyIndex;
        this.pvvKey = builder.pvvKey;
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
                "pin: " + pin + ", " +
                "pinKeyIndex: " + pvvKeyIndex + ", " +
                "pvvKey: " + pvvKey +
                " }";
    }

    /**
     * Public builder method for initiating sequential build
     * @return new Builder class instance.
     */
    public static PanSetter builder(){
        return new GeneratePvvCommand.Builder();
    }

    /**
     * Builder interface for verifying and setting PAN.
     */
    public interface PanSetter{
        /**
         * Method for verification and setting of PAN.
         * @param pan PAN to be verified and set.
         * @return pinSetter interface for setting PIN.
         */
        PinSetter setPan(String pan);
    }

    /**
     * Builder interface for verifying and setting PIN.
     */
    public interface PinSetter{
        /**
         * Method for verification and setting of PIN.
         * @param pin PIN to be verified and set.
         * @return pinKeyIndexSetter interface for setting PIN Key Index.
         */
        PvvKeyIndexSetter setPin(String pin);
    }

    /**
     * Builder interface for verifying and setting PIN Key Index.
     */
    public interface PvvKeyIndexSetter{
        /**
         * Method for verification and setting of PIN Key Index.
         * @param pvvKeyIndex PVV Key Index to be verified and set.
         * @return pvvKeySetter interface for setting PIN Verification Value Key.
         */
        PvvKeySetter setPvvKeyIndex(String pvvKeyIndex);
    }

    /**
     * Builder interface for verifying and setting PIN Verification Value Key.
     */
    public interface PvvKeySetter{
        /**
         * Method for verification and setting of PIN Verification Value Key.
         * @param pvvKey PVV Key to be verified and set.
         * @return pvvKeySetter interface for setting PIN Verification Value Key.
         */
        FinishBuild setPvvKey(String pvvKey);
    }

    /**
     * Builder interface for finishing the build of the Generate PVV Command object.
     */
    public interface FinishBuild{
        /**
         * Method for building a new GeneratePvvCommand object instance.
         * @return GeneratePvvCommand object.
         */
        GeneratePvvCommand build();
    }

    /**
     * Private class implementing the builder interfaces and defining the concrete methods to be used for Generate PVV Command object build.
     */
    private static class Builder implements PanSetter, PinSetter, PvvKeyIndexSetter, PvvKeySetter, FinishBuild{

        private String pan;
        private String pin;
        private String pvvKeyIndex;
        private String pvvKey;

        /**
         * Method for verification and setting of PAN.
         *
         * @param pan PAN to be verified and set.
         * @return pinSetter interface for setting PIN.
         */
        @Override
        public PinSetter setPan(String pan) {
            log.debug(this.getClass().getSimpleName() + " Pan received: {}.", pan);
            if (Validator.isPanValid(pan)){
                this.pan = pan;
            } else {
                throw new CommonException(MSG_GEN_PVV_CMD_PAN_NOT_NUM,
                        ERR_PVVFNGN001,
                        Response.Status.BAD_REQUEST);
            }
            return this;
        }

        /**
         * Method for verification and setting of PIN.
         *
         * @param pin PIN to be verified and set.
         * @return pinKeyIndexSetter interface for setting PIN Key Index.
         */
        @Override
        public PvvKeyIndexSetter setPin(String pin) {
            log.debug(this.getClass().getSimpleName() + " Pin received: {}.", pin);
            if (Validator.isPinValid(pin)){
                this.pin = pin;
            } else {
                throw new CommonException(MSG_GEN_PVV_CMD_PIN_INVALID,
                        ERR_PVVFNGN002,
                        Response.Status.BAD_REQUEST);
            }
            return this;
        }

        /**
         * Method for verification and setting of PIN Key Index.
         *
         * @param pvvKeyIndex PVV Key Index to be verified and set.
         * @return pvvKeySetter interface for setting PIN Verification Value Key.
         */
        @Override
        public PvvKeySetter setPvvKeyIndex(String pvvKeyIndex) {
            log.debug(this.getClass().getSimpleName() + " PvvKeyIndex received: {}.", pvvKeyIndex);
            if (Validator.isKeyIndexValid(pvvKeyIndex)){
                this.pvvKeyIndex = pvvKeyIndex;
            } else{
                throw new CommonException(MSG_GEN_PVV_CMD_PIN_KEY_INDEX_INVALID,
                        ERR_PVVFNGN003,
                        Response.Status.BAD_REQUEST);
            }
            return this;
        }

        /**
         * Method for verification and setting of PIN Verification Value Key.
         *
         * @param pvvKey PIN Key Index to be verified and set.
         * @return pvvKeySetter interface for setting PIN Verification Value Key.
         */
        @Override
        public FinishBuild setPvvKey(String pvvKey) {
            log.debug(this.getClass().getSimpleName() + " PvvKey received: {}.", pvvKey);
            if (Validator.isTdeaKeyValid(pvvKey)){
                this.pvvKey = pvvKey;
            } else {
                throw new CommonException(MSG_GEN_PVV_CMD_PVK_INVALID,
                        ERR_PVVFNGN004,
                        Response.Status.BAD_REQUEST);
            }
            return this;
        }

        /**
         * Method for building a new GeneratePvvCommand object instance.
         * @return GeneratePvvCommand object.
         */
        @Override
        public GeneratePvvCommand build() {
            return new GeneratePvvCommand(this);
        }
    }

}