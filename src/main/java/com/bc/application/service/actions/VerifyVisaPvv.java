package com.bc.application.service.actions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * This class defines methods required for Verifying a Visa Pin Verification value for a given PIN.
 */
@Getter
@Slf4j
public class VerifyVisaPvv {

    private final String pvvToVerify;
    private final String generatedPvv;
    private final boolean isPvvVerified;

    /**
     * Private constructor to be used by the builder pattern method to instantiate the object.
     * @param builder Builder pattern object with values set.
     */
    private VerifyVisaPvv(Builder builder){

        this.pvvToVerify = builder.pvvToVerify;
        this.generatedPvv = builder.generatedPvv;
        this.isPvvVerified = builder.isPvvVerified;

    }

    /**
     * Override default toString method to generate a string representation of class attributes for logging.
     * @return String representation of class attributes.
     */
    @Override
    public String toString(){
        return this.getClass().getSimpleName() +
                " { " +
                "pvvToVerify: " + pvvToVerify + ", " +
                "generatedPvv: " + generatedPvv + ", " +
                "isPvvVerified: " + isPvvVerified +
                " }";
    }

    /**
     * Public builder method for initiating sequential builder methods for Visa Pin Verification Value generation.
     * @return new Builder class instance.
     */
    public static SetPvv build(){
        return new Builder();
    }

    /**
     * Builder interface defining method for setting the PVV to be verified.
     */
    public interface SetPvv{

        /**
         * Method for setting PVV to be verified.
         *
         * @param pvv PVV to be verified.
         * @return PVV to verify attribute set.
         */
        SetGeneratedPvv setPvv(String pvv);

    }

    /**
     * Builder interface defining method for setting the PVV generated from request.
     */
    public interface SetGeneratedPvv{

        /**
         * Method for setting the PVV generated from request.
         *
         * @param generatedPvv PVV generated from request.
         * @return Generated PVV attribute set.
         */
        VerifyPvv setGeneratedPvv(String generatedPvv);

    }

    /**
     * Builder interface defining method for verification of the PVV supplied against the generated PVV.
     */
    public interface VerifyPvv{
        /**
         * Method for verifying a PVV supplied against the generated PVV.
         *
         * @return isPvvVerified attribute set to true, when verification successful, else set to false.
         */
         FinishBuild verifyPvv();

    }

    /**
     *  Interface defining the final method in the builder sequence.
     */
    public interface FinishBuild{
        /**
         * Method for building the response data.
         * @return Generated PIN.
         */
        VerifyVisaPvv build();
    }

    /**
     * Private class implementing the builder interfaces and defining the concrete methods to be used for Visa Pin Verification Value generation.
     */
    private static class Builder implements SetPvv, SetGeneratedPvv, VerifyPvv, FinishBuild{

        private String pvvToVerify;
        private String generatedPvv;
        private boolean isPvvVerified;

        /**
         * Method for setting PVV to be verified.
         *
         * @param pvvToVerify PVV to be verified.
         * @return PVV to verify attribute set.
         */
        @Override
        public Builder setPvv(String pvvToVerify) {
            this.pvvToVerify = pvvToVerify;
            return this;
        }

        /**
         * Method for setting the PVV generated from request.
         *
         * @param generatedPvv PVV generated from request.
         * @return Generated PVV attribute set.
         */
        @Override
        public Builder setGeneratedPvv(String generatedPvv) {
            this.generatedPvv = generatedPvv;
            return this;
        }

        /**
         * Method for verifying a PVV supplied against the generated PVV.
         *
         * @return isPvvVerified attribute set to true, when verification successful, else set to false.
         */
        @Override
        public Builder verifyPvv() {
            isPvvVerified = pvvToVerify.equals(generatedPvv);
            return this;
        }

        /**
         * Method for building the response data.
         *
         * @return Generate Visa Pin Verification Value action object.
         */
        @Override
        public VerifyVisaPvv build() {
            return new VerifyVisaPvv(this);
        }

    }

}
