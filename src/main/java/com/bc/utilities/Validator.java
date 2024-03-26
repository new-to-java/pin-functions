package com.bc.utilities;

import lombok.extern.slf4j.Slf4j;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Utility class hosting methods for performing data validations
 */
@Slf4j
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
     * Verify input Tdea Key and ensure it contains either 16, 32 or 48 hexadecimal digits only.
     * @param tdeaKey PIN Verification Key to be verified.
     * @return True when pinVerificationKey is valid, else returns False.
     */
    public static boolean isTdeaKeyValid(String tdeaKey){
        final String TDEA_KEY_PATTERN = "^[\\dA-Fa-f]{16}|\\d{32}|\\d{48}$";
        return Objects.nonNull(tdeaKey) &&
                Pattern.matches(TDEA_KEY_PATTERN, tdeaKey);
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
                ((parsedPinLength >= 4 &&
                        parsedPinLength <= 12));
    }


    /**
     * Verify input keyIndex and ensure it contains decimal digits only and is in the range 1 to 9.
     * @param pvvKeyIndex Key Index to be verified.
     * @return True when keyIndex is valid, else returns False.
     */
    public static boolean isPvvKeyIndexValid(String pvvKeyIndex){
        final String KEY_INDEX_PATTERN = "^[1-9]$";
        int parsedKeyIndex = 0;
        boolean isNotNullAndMatchesPattern = false;
        if ((Objects.nonNull(pvvKeyIndex)) && (Pattern.matches(KEY_INDEX_PATTERN, pvvKeyIndex))) {
            isNotNullAndMatchesPattern = true;
            parsedKeyIndex = Integer.parseInt(pvvKeyIndex);
        }
        return isNotNullAndMatchesPattern &&
                ((parsedKeyIndex >= 1 &&
                        parsedKeyIndex <= 9));
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

    /**
     * Verify input PIN and ensure it contains decimal digits only and is 4 to 12 digits long.
     * @param pin PIN to be verified.
     * @return True when PVV is valid, else returns False.
     */
    public static boolean isPinValid(String pin){
        final String PIN_PATTERN = "^\\d{1,12}$";
        return Objects.nonNull(pin) &&
                Pattern.matches(PIN_PATTERN, pin) && // Must be all numeric digits
                // must be comprised of 4 to 12 digits
                ((pin.length() >= 4) &&
                        (pin.length() <= 12));
    }

    /**
     * Verify input PVV and ensure it contains decimal digits only and is exactly 4 digits long.
     * @param pvv PVV to be verified.
     * @return True when PVV is valid, else returns False.
     */
    public static boolean isPvvValid(String pvv){
        final String PVV_PATTERN = "^\\d{4}$";
        // Must be all numeric and exactly 4 digits long
        return Objects.nonNull(pvv) &&
                Pattern.matches(PVV_PATTERN, pvv);
    }

}