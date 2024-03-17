package com.bc.exception;

/**
 * Class defining static attributes to be used for error code and error message definition.
 */
public class ErrorConstants {
    // TripleDes - Error codes and error message definitions.
    public static final String ERR_CRYINAL000 = "CRYINAL000";
    public static final String MSG_CIPHER_ALGORITHM_INIT_FAILURE = "Cipher object algorithm initialization failed.";
    public static final String ERR_CRYINKY000 = "CRYINKY000";
    public static final String MSG_CIPHER_KEY_INIT_FAILURE = "Key decoding to byte array failed.";
    public static final String ERR_CRYINKY001 = "CRYINKY001";
    public static final String MSG_CIPHER_ENC_KEY_INVALID_FAILURE = "Cipher encrypt object initialization failed due to invalid key.";
    public static final String ERR_CRYINKY002 = "CRYINKY002";
    public static final String MSG_CIPHER_DEC_KEY_INVALID_FAILURE = "Cipher decrypt object initialization failed due to invalid key.";
    public static final String ERR_CRYTEXT000 = "CRYTEXT000";
    public static final String MSG_CIPHER_TEXT_DECODE_FAILURE = "Clear text data decoding to byte array failed.";
    public static final String ERR_CRYENDE000 = "CRYENDE000";
    public static final String MSG_CIPHER_ENC_DEC_OPERATION_FAILURE = "Encrypt/Decrypt operation failed.";
    // GeneratePinCommand - Error codes and error message definitions.
    public static final String ERR_PINFNGN001 = "PINFNGN001";
    public static final String MSG_GEN_PIN_CMD_PAN_NOT_NUM = "PAN must be numeric and 16 digits long.";
    public static final String ERR_PINFNGN002 = "PINFNGN002";
    public static final String MSG_GEN_PIN_CMD_PVK_INVALID = "PIN Verification Key (PVK) must be comprised of 16, 32, 48 hexadecimal digits only.";
    public static final String ERR_PINFNGN003 = "PINFNGN003";
    public static final String MSG_GEN_PIN_CMD_PIN_LEN_INVALID = "PIN Length must be numeric, and in the range 4 to 12.";
    public static final String ERR_PINFNGN004 = "PINFNGN004";
    public static final String MSG_GEN_PIN_CMD_PIN_OFFSET_INVALID = "PIN Offset length must be greater than or equal to PIN Length, and must be comprised of 4 to 12 digits.";
    // GeneratePvvCommand - Error codes and error message definitions.
    public static final String ERR_PVVFNGN001 = "PVVFNGN001";
    public static final String MSG_GEN_PVV_CMD_PAN_NOT_NUM = "PAN must be numeric and 16 digits long.";
    public static final String ERR_PVVFNGN002 = "PVVFNGN002";
    public static final String MSG_GEN_PVV_CMD_PIN_INVALID = "PIN must be numeric, and length of PIN must be in the range 4 to 12.";
    public static final String ERR_PVVFNGN003 = "PVVFNGN003";
    public static final String MSG_GEN_PVV_CMD_PVK_INVALID = "PIN Verification Value Key (PVV Key) must be comprised of 16, 32, 48 hexadecimal digits only.";
    public static final String ERR_PVVFNGN004 = "PVVFNGN004";
    public static final String MSG_GEN_PVV_CMD_PIN_KEY_INDEX_INVALID = "PIN Key Index must be numeric and in the range 1 and 9.";

}
