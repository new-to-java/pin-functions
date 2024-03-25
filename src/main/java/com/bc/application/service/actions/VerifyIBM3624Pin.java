package com.bc.application.service.actions;

import lombok.Getter;

/**
 * This class defines methods required for verifying an IBM 3624 PIN based on an offset.
 */
@Getter
public class VerifyIBM3624Pin {
    String pinToVerify;
    String generatedPin;
    boolean pinVerified;


    /**
     * Override default toString method to generate a string representation of class attributes for logging.
     * @return String representation of class attributes.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                " { " +
                "pinToVerify: " + pinToVerify + ", " +
                "generatedPin: " + generatedPin + ", " +
                "pinVerified: " + pinVerified +
                " }";
    }

    /**
     * Private constructor to be used by the builder pattern method to instantiate the object.
     *
     * @param builder Builder pattern object with values set.
     */
    private VerifyIBM3624Pin(Builder builder) {

        this.pinVerified = builder.pinVerified;
        this.pinToVerify = builder.pinToVerify;
        this.generatedPin = builder.generatedPin;

    }

    /**
     * Public builder method for initiating sequential builder methods for IBM 3624 PIN verification.
     * @return new Builder class instance.
     */
    public static GetGeneratedPin builder(){
        return new Builder();
    }

    /**
     * Interface defining method for generating an IBM 3624 PIN based on offset.
     */
    public interface GetGeneratedPin{
        /**
         * Method for retrieving the IBM 3624 PIN generated based on the values supplied as input.
         * @return Generated PIN.
         */
        GetPinToVerify getGeneratedPin(String generatedPin);
    }

    /**
     * Interface defining method for getting the PIN to be verified.
     */
    public interface GetPinToVerify{
        /**
         * Method for getting the PIN to be verified from the request.
         * @param pinToVerify PIN to be verified.
         * @return PIN to be verified.
         */
        VerifyPin setPin(String pinToVerify);
    }

    /**
     * Interface defining method for comparing the generated PIN against the PIN supplied.
     */
    public interface VerifyPin{
        /**
         * Method to compare the generated PIN against the PIN received from request and set the pinVerified attribute.
         * @return True if PIN is verified successfully, else return false.
         */
        FinishBuild verifyPin();
    }

    /**
     * Interface defining the final build method in the builder pattern.
     */
    public interface FinishBuild{
        /**
         * Method for finishing the build of the Verify IBM 3624 PIN and returning the result.
         * @return Verify IBM 3624 PIN object containing the result.
         */
        VerifyIBM3624Pin build();
    }

    static class Builder implements GetGeneratedPin, GetPinToVerify, VerifyPin, FinishBuild {

        String pinToVerify;
        String generatedPin;
        boolean pinVerified;

        /**
         * Method for retrieving the IBM 3624 PIN generated based on the values supplied as input.
         *
         * @return Generated PIN.
         */
        @Override
        public Builder getGeneratedPin(String generatedPin) {

            this.generatedPin = generatedPin;
            return this;
        }

        /**
         * Method for getting the PIN to be verified from the request.
         *
         * @param pinToVerify PIN to be verified.
         * @return PIN to be verified.
         */
        @Override
        public VerifyPin setPin(String pinToVerify) {
            this.pinToVerify = pinToVerify;
            return this;
        }

        /**
         * Method to compare the generated PIN against the PIN received from request and set the pinVerified attribute.
         *
         * @return True if PIN is verified successfully, else return false.
         */
        @Override
        public FinishBuild verifyPin() {
            pinVerified = pinToVerify.equals(generatedPin);
            return this;
        }

        /**
         * Method for finishing the build of the Verify IBM 3624 PIN and returning the result.
         *
         * @return Verify IBM 3624 PIN object containing the result.
         */
        @Override
        public VerifyIBM3624Pin build() {
            return new VerifyIBM3624Pin(this);
        }
    }

}