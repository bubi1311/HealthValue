package de.clever.healthvalue.util.validators;

import android.widget.EditText;

public class ValidatorErrorMessages {


    /**
     * Sets an custom error message to an editText
     *
     * @param editText    - The EditText-Object.
     * @param errorString - The error message.
     */
    public static void setError(EditText editText, String errorString) {

        editText.setError(errorString);

    }

    /**
     * Clears a former set error message o
     *
     * @param editText - The EditText-object that should be cleaned.
     */
    public static void clearError(EditText editText) {

        editText.setError(null);

    }
}
