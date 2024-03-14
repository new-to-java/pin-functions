package com.bc.application.port.in.rest.command;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Command object for Generate PIN request
 */
@Getter
@Setter
@Slf4j
public class GeneratePinCommand {

    private String pan;
    private String pinVerificationKey;
    private String pinLength;
    private String pinOffset;

    /**
     * Constructor for builder to instantiate the Generate Pin Command object
     * @param builder Builder object.
     */
    private GeneratePinCommand(Builder builder){
        this.pan = builder.pan;
        this.pinVerificationKey = builder.pinVerificationKey;
        this.pinLength = builder.pinLength;
        this.pinOffset = builder.pinOffset;

        log.debug("Command object created: {}.", this.toString());

    }

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
        PinVerificationKeySetter pan(String pan);
    }

    /**
     * Builder interface for setting PinVerificationKey
     */
    public interface PinVerificationKeySetter{
        PinLengthSetter pinVerificationKey(String pinVerificationKey);
    }

    /**
     * Builder interface for setting PinLength
     */
    public interface PinLengthSetter{
        PinOffsetSetter pinLength(String pinLength);
    }

    /**
     * Builder interface for setting PinOffset
     */
    public interface PinOffsetSetter{
        FinishBuild pinOffset(String pinOffset);

    }

    /**
     * Builder interface for building the object
     */
    public interface FinishBuild{
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

        @Override
        public Builder pan(String pan){
            this.pan = pan;
            return this;
        }

        @Override
        public Builder pinVerificationKey(String pinVerificationKey){
            this.pinVerificationKey = pinVerificationKey;
            return this;
        }

        @Override
        public Builder pinLength(String pinLength){
            this.pinLength = pinLength;
            return this;
        }

        @Override
        public Builder pinOffset(String pinOffset){
            this.pinOffset = pinOffset;
            return this;
        }

        @Override
        public GeneratePinCommand build(){
            return new GeneratePinCommand(this);
        }

    }
}