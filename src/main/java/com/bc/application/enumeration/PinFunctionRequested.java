package com.bc.application.enumeration;

public enum PinFunctionRequested {

    PIN_GENERATE("GEN_PIN"),
    PIN_VERIFY("VER_PIN");
    public final String value;

    /**
     * Constructor for enum.
     * @param value attribute storing the current value of the enum.
     */
    PinFunctionRequested(String value) {
        this.value = value;
    }

    /**
     * Method to check if the enum object value is set to GEN_PIN.
     * @return True when value is set to GEN_PIN.
     */
    public boolean isGeneratePin(){
        return this.equals(PIN_GENERATE);
    }

    /**
     * Method to check if the enum object value is set to VER_PIN.
     * @return True when value is set to VER_PIN.
     */
    public boolean isVerifyPin(){
        return this.equals(PIN_VERIFY);
    }

}