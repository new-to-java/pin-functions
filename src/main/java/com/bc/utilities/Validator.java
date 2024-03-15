package com.bc.utilities;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Utility class hosting methods for performing data validations
 */
public class Validator {

    /**
     * Verify input pan and ensure it contains 16 decimal digits only.
     * @param pan PAN to be verified.
     * @return True when pan is valid, else returns False.
     */
    public static boolean isPanValid(String pan){
        final String PAN_PATTERN = "^\\d{16}$";
        return Objects.nonNull(pan) &&
                Pattern.matches(PAN_PATTERN, pan);
    }

    /**
     * Verify input pinVerificationKey and ensure it contains either 16, 32 or 48 hexadecimal digits only.
     * @param pinVerificationKey PIN Verification Key to be verified.
     * @return True when pinVerificationKey is valid, else returns False.
     */
    public static boolean isPinVerificationKeyValid(String pinVerificationKey){
        final String PVK_PATTERN = "^[\\dA-Fa-f]{16}|\\d{32}|\\d{48}$";
        return Objects.nonNull(pinVerificationKey) &&
                Pattern.matches(PVK_PATTERN, pinVerificationKey);
    }

    /**
     * Verify input pinLength and ensure it contains decimal digits only and is in the range 4 to 12.
     * @param pinLength PIN Length to be verified.
     * @return True when pinLength is valid, else returns False.
     */
    public static boolean isPinLengthValid(String pinLength){
        final String PIN_LENGTH_PATTERN = "^\\d{1,2}$";
        int parsedPinLength = 0;
        if (Objects.nonNull(pinLength)) {
            parsedPinLength = Integer.parseInt(pinLength);
        }
        return Objects.nonNull(pinLength) &&
                Pattern.matches(PIN_LENGTH_PATTERN, pinLength) &&
                (parsedPinLength >= 4 && parsedPinLength <= 12);
    }

    /**
     * Verify input pinOffset and ensure it contains decimal digits only and is 4 to 12 digits long.
     * @param pinOffset PIN Offset to be verified.
     * @param pinLength PIN Length to compare the PIN Offset length.
     * @return True when pinOffset is valid, else returns False.
     */
    public static boolean isPinOffsetValid(String pinLength, String pinOffset){
        final String PIN_OFFSET_PATTERN = "^\\d{1,12}$";
        int parsedPinLength = Integer.parseInt(pinLength);
        return Objects.nonNull(pinOffset) &&
                Pattern.matches(PIN_OFFSET_PATTERN, pinOffset) && // Must be all numeric digits
                // must be greater than or equal to PIN Length
                (parsedPinLength <= pinOffset.length()) &&
                // must be comprised of 4 to 12 digits
                ((pinOffset.length() >= 4) &&
                        (pinOffset.length() <= 12));
    }

}