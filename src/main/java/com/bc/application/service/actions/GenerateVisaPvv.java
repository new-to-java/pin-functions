package com.bc.application.service.actions;

import com.bc.utilities.TripleDES;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.util.regex.Pattern;

/**
 * This class defines methods required for generating a Visa Pin Verification value for a given PIN.
 */
@Getter
@Slf4j
public class GenerateVisaPvv {

    private final String pin;
    private final String pvv;

    /**
     * Private constructor to be used by the builder pattern method to instantiate the object.
     * @param builder Builder pattern object with values set.
     */
    private GenerateVisaPvv(Builder builder){

        this.pin = builder.pin;
        this.pvv = builder.pvv;

    }

    /**
     * Override default toString method to generate a string representation of class attributes for logging.
     * @return String representation of class attributes.
     */
    @Override
    public String toString(){
        return this.getClass().getSimpleName() +
                " { " +
                "pin: " + pin + ", " +
                "pvv: " + pvv +
                " }";
    }

    /**
     * Public builder method for initiating sequential builder methods for Visa Pin Verification Value generation.
     * @return new Builder class instance.
     */
    public static BuildTransformedSecurityParameter build(){
        return new Builder();
    }

    /**
     * Builder interface defining method for building Transformed Security Parameter (TSP).
     */
    public interface BuildTransformedSecurityParameter{

        /**
         * Method for generating Transformed Security Parameter (TSP) from Pan, PvvKeyIndex and Pin.
         * @param pan PAN for PVV generation.
         * @param pvvKeyIndex PVV Key Index.
         * @param pin Pin for PVV generation.
         * @return Transformed Security Parameter.
         */
        EncryptTspWithPvvKey buildTransformedSecurityParameter(String pan, String pvvKeyIndex, String pin);

    }

    /**
     * Builder interface defining method for encrypting TSP with Pin Verification Value Key.
     */
    public interface EncryptTspWithPvvKey{

        /**
         * Method for encrypting TSP with the supplied Pin Verification Value Key.
         * @param pvvKey Pin Verification Value Key.
         * @return TSP encrypted using PVV Key.
         */
        GetPinVerificationValue encryptTspWithPvvKey(String pvvKey);

    }

    /**
     * Builder interface defining method for generating Pin Verification Value.
     */
    public interface GetPinVerificationValue{
        /**
         * Method for generating a Visa Pin Verification Value.
         * @return Visa Pin Verification Value.
         */
         FinishBuild getPinVerificationValue();

    }

    /**
     *  Interface defining the final method in the builder sequence.
     */
    public interface FinishBuild{
        /**
         * Method for building the response data.
         * @return Generated PIN.
         */
        GenerateVisaPvv build();
    }

    /**
     * Private class implementing the builder interfaces and defining the concrete methods to be used for Visa Pin Verification Value generation.
     */
    private static class Builder implements BuildTransformedSecurityParameter, EncryptTspWithPvvKey, GetPinVerificationValue, FinishBuild{

        private String pin;
        private String pvv;
        private StringBuilder tsp;
        private String encryptedTsp;

        /**
         * Method for generating Transformed Security Parameter (TSP) from Pan, PvvKeyIndex and Pin.
         *
         * @param pan         PAN for PVV generation.
         * @param pvvKeyIndex PVV Key Index.
         * @param pin         Pin for PVV generation.
         * @return Transformed Security Parameter.
         */
        @Override
        public EncryptTspWithPvvKey buildTransformedSecurityParameter(String pan, String pvvKeyIndex, String pin) {
            log.debug(this.getClass().getSimpleName() + " Pan received: {}.", pan);
            log.debug(this.getClass().getSimpleName() + " PvvKeyIndex received: {}.", pvvKeyIndex);
            log.debug(this.getClass().getSimpleName() + " Pin received: {}.", pin);
            // 16 digit PAN assumed
            final int PAN_EXTRACT_START_INDEX = 4; //Start from 5th digit
            final int PAN_EXTRACT_END_INDEX = 15; // Exclude check digit
            // PIN offset Values
            final int PIN_EXTRACT_START_INDEX = 0; //Start from 5th digit
            final int PIN_EXTRACT_END_INDEX = 4; // Exclude check digit
            // Set PIN attribute and build TSP
            this.pin = pin;
            this.tsp = new StringBuilder();
            // Extract 11 rightmost digits of 16 digit PAN excluding check digit
            this.tsp.append(pan, PAN_EXTRACT_START_INDEX, PAN_EXTRACT_END_INDEX);
            this.tsp.append(pvvKeyIndex);
            // PIN is being trimmed to 4 bytes, since regardless of the PIN length, the PVV seems to be always 4 digits
            // so left most 4 digits are always extracted and included in TSP derivation. This needs to be confirmed.
            this.tsp.append(pin, PIN_EXTRACT_START_INDEX, PIN_EXTRACT_END_INDEX);
            return this;
        }

        /**
         * Method for encrypting TSP with the supplied Pin Verification Value Key.
         *
         * @param pvvKey Pin Verification Value Key.
         * @return TSP encrypted using PVV Key.
         */
        @Override
        public GetPinVerificationValue encryptTspWithPvvKey(String pvvKey) {
            log.debug(this.getClass().getSimpleName() + " PvvKey received: {}.", pvvKey);
            encryptedTsp = TripleDES.encrypt(tsp.toString(), pvvKey);
            return this;
        }

        /**
         * Method for generating a Visa Pin Verification Value.
         *
         * @return Visa Pin Verification Value.
         */
        @Override
        public FinishBuild getPinVerificationValue() {
            StringBuilder numericTspDigits = new StringBuilder();
            StringBuilder decimalisedTspDigits = new StringBuilder();
            // Extract PVV from encrypted TSP
            for (int i = 0; i < encryptedTsp.length(); i++) {
                String currentTspChar = encryptedTsp.substring(i, i + 1);
                if (isNumeric(currentTspChar)){
                    numericTspDigits.append(currentTspChar);
                } else {
                    // If not numeric, decimalise the TSP character
                    decimalisedTspDigits.append((Integer.parseInt(currentTspChar, 16)) - 10);
                }
                // Break if Pin Verification Value has already been found
                if (numericTspDigits.length() == 4){
                    pvv = String.valueOf(numericTspDigits);
                    break;
                }
            }
            // Check if 4 numeric digits were found
            if (numericTspDigits.length() != 4 ){
                numericTspDigits.append(decimalisedTspDigits);
                pvv = numericTspDigits.substring(0, 4);
            }

            return this;
        }

        /**
         * Verify if a string supplied is numeric or not.
         * @param stringToCheck String to be verified.
         * @return True if stringToCheck is numeric, else return false.
         */
        private boolean isNumeric(String stringToCheck){
            final String numberPattern = "^[0-9]$";
            return Pattern.matches(numberPattern, stringToCheck);
        }

        /**
         * Method for building the response data.
         *
         * @return Generate Visa Pin Verification Value action object.
         */
        @Override
        public GenerateVisaPvv build() {
            return new GenerateVisaPvv(this);
        }
    }

}
