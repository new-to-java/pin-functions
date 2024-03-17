package com.bc.utilities;

import com.bc.exception.CommonException;
import jakarta.ws.rs.core.Response;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import static com.bc.exception.ErrorConstants.*;

/**
 * Class implementing wrapper functions for Triple Data Encryption Standard (TDES) Algorithm functions
 * using java standard Crypto libraries.
 */
@Slf4j
@Setter
public class TripleDES {
    private static String inputData;
    private static String key;
    private static final String DES_EDE = "DESede";
    private static final String MODE = "ECB";
    private static final String PADDING = "NoPadding";
    private static final boolean ENCRYPT = true;
    private static final boolean DECRYPT = false;
    @Setter(AccessLevel.NONE)
    private static String workTdeaInputKey;
    @Setter(AccessLevel.NONE)
    private static Cipher des;
    @Setter(AccessLevel.NONE)
    private static Key secretKey;
    @Setter(AccessLevel.NONE)
    private static String outputData;
    /**
     * Method to perform a Triple DES encryption of a clear text using a key passed
     * @return Cipher text generated from clear text after encryption.
     */
    public static String encrypt(String data, String pvkKey){
        inputData = data;
        key = pvkKey;
        initialize(ENCRYPT);
        byte [] decodedInputData = decodeInputDataTextToByteArray();
        byte [] desEdeOutputData = runDESede(decodedInputData);
        outputData = Hex.encodeHexString(desEdeOutputData);
        log.debug("TDEA Encryption request processed!");
        log.debug("Encrypted data: {}.", outputData);
        return outputData;
    }
    /**
     * Method to perform a Triple DES decryption of a cipher text using a key passed
     * @return Clear text generated from cipher text after decryption.
     */
    public static String decrypt(String data, String pvkKey){
        inputData = data;
        key = pvkKey;
        initialize(DECRYPT);
        byte [] decodedInputData = decodeInputDataTextToByteArray();
        byte [] desEdeOutputData = runDESede(decodedInputData);
        outputData = Hex.encodeHexString(desEdeOutputData);
        log.debug("TDEA Decryption request processed!");
        log.debug("Decrypted data: {}.", outputData);
        return outputData;
    }
    /**
     * Method to initialize the Cipher object to set algorithm to DESede and perform encryption or decryption.
     * @param encrypt When set to true, will initialise the Cipher object to DESede encrypt,
     *                else the Cipher object will be set to DESede decrypt.
     */
    private static void initialize(boolean encrypt) {
        log.debug(TripleDES.class + " input data: {}, key: {}.", inputData, key);
        initializeDESAlgorithm();
        initializeDESedeKey();
        if (encrypt) {
            initializeForDesEncryption();
        } else {
            initializeForDesDecryption();
        }
    }
    /**
     * Initialize Cipher object des with the DESede algorithm with desired mode and padding.
     */
    private static void initializeDESAlgorithm(){
        StringBuilder algorithmWithModeAndPadding = new StringBuilder();
        algorithmWithModeAndPadding.append(DES_EDE)
                .append("/")
                .append(MODE)
                .append("/")
                .append(PADDING);

        try {
            des = Cipher.getInstance(algorithmWithModeAndPadding.toString());
        } catch (NoSuchPaddingException |
                 NoSuchAlgorithmException cipherException) {
            exceptionLogger(cipherException);
            log.debug(ERR_CRYINAL000 + MSG_CIPHER_ALGORITHM_INIT_FAILURE);
            throwExceptionAndTerminate(ERR_CRYINAL000);
        }
    }
    /**
     * Decode the plaintext key to byte array and initialize the Key object.
     */
    private static void initializeDESedeKey() {
        try {
            convertToTripleLengthTDEAKey();
            secretKey = new SecretKeySpec(Hex.decodeHex(
                    workTdeaInputKey),
                    DES_EDE
            );
        } catch (DecoderException decoderException){
            exceptionLogger(decoderException);
            log.debug(ERR_CRYINKY000 + MSG_CIPHER_KEY_INIT_FAILURE);
            throwExceptionAndTerminate(ERR_CRYINKY000);
        }
    }
    /**
     * Initialize Cipher object for encryption.
     */
    private static void initializeForDesEncryption(){
        try {
            des.init(Cipher.ENCRYPT_MODE,
                    secretKey
            );
        } catch (InvalidKeyException invalidKeyException){
            exceptionLogger(invalidKeyException);
            log.debug(ERR_CRYINKY001 + MSG_CIPHER_ENC_KEY_INVALID_FAILURE);
            throwExceptionAndTerminate(ERR_CRYINKY001);
        }
    }
    /**
     * Initialize Cipher object for decryption.
     */
    private static void initializeForDesDecryption(){
        try {
            des.init(Cipher.DECRYPT_MODE,
                    secretKey
            );
        } catch (InvalidKeyException invalidKeyException) {
            exceptionLogger(invalidKeyException);
            log.debug(ERR_CRYINKY002 + MSG_CIPHER_DEC_KEY_INVALID_FAILURE);
            throwExceptionAndTerminate(ERR_CRYINKY002);
        }
    }
    /**
     * Transform the input data to byte array.
     */
    private static byte [] decodeInputDataTextToByteArray(){
        byte [] decodedHexData = new byte[0];
        try {
            decodedHexData = Hex.decodeHex(inputData);
        } catch (DecoderException decoderException){
            exceptionLogger(decoderException);
            log.debug(ERR_CRYTEXT000 + MSG_CIPHER_TEXT_DECODE_FAILURE);
            throwExceptionAndTerminate(ERR_CRYTEXT000);
        }
        return decodedHexData;
    }
    /**
     * Run the DESede algorithm.
     */
    private static byte [] runDESede(byte [] decodedInputData) {
        byte [] cryptoOutputData = new byte[0];
        try
        {
            cryptoOutputData = des.doFinal(decodedInputData);
        } catch (IllegalBlockSizeException |
                 BadPaddingException cryptoException){
            exceptionLogger(cryptoException);
            log.debug(ERR_CRYENDE000 + MSG_CIPHER_ENC_DEC_OPERATION_FAILURE);
            throwExceptionAndTerminate(ERR_CRYENDE000);
        }
        return cryptoOutputData;
    }
    /**
     * Method to convert a single or double length TDEA key to a triple length TDEA key.
     * Note: Parity check is not implemented and will be done in a future release.
     */
    private static void convertToTripleLengthTDEAKey(){
        final int KEY_LENGTH_TDEA_SINGLE = 16;
        final int KEY_LENGTH_TDEA_DOUBLE = 32;
        if (key.length() == KEY_LENGTH_TDEA_SINGLE) {
            workTdeaInputKey = key + key + key;
            log.debug(
                    "Single length TDEA Key received {}, Key expanded to triple length TDEA Key: {}.",
                    key,
                    workTdeaInputKey
            );
        } else if (key.length() == KEY_LENGTH_TDEA_DOUBLE) {
            workTdeaInputKey = key + key.substring(0,
                    KEY_LENGTH_TDEA_SINGLE
            );
            log.debug(
                    "Double length TDEA Key received {}, Key expanded to triple length TDEA Key: {}.",
                    key,
                    workTdeaInputKey
            );
        } else {
            workTdeaInputKey = key;
            log.debug(
                    "Triple length TDEA Key input: {}.",
                    workTdeaInputKey
            );
        }
    }
    /**
     * Override method for the object's default toString method.
     * @return String representing object's attribute values.
     */
    @Override
    public String toString() {

        return "{" +
                "inputData='" + inputData + '\'' +
                ", key='" + key + '\'' +
                ", outputData='" + outputData + '\'' +
                '}';

    }
    /**
     * Method to raise an exception and terminate processing.
     * @param errorCode Error code to uniquely identify the exception reason.
     */
    private static void throwExceptionAndTerminate(String errorCode) {
        throw new CommonException("Technical exception encountered, please contact programmer.",
                errorCode,
                Response.Status.INTERNAL_SERVER_ERROR
        );
    }

    /**
     * Generic private exception logger method for logging a more detailed error details.
     * @param e Exception object thrown
     */
    private static void exceptionLogger(Exception e){
        log.debug(TripleDES.class + " has thrown exception \"{}\".", e.getClass().getSimpleName());
        log.debug(TripleDES.class + " Exception message \"{}\".", e.getMessage());
    }

}
