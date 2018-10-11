package de.clever.healthvalue.util.validators;

import android.widget.EditText;


import de.clever.healthvalue.util.Range;
import de.clever.healthvalue.util.StringUtils;
import com.google.common.primitives.Doubles;

public class ValidatorEditTextNumberRange {
    private final Range myRange;
    private final EditText myEditText;


    public ValidatorEditTextNumberRange(EditText editText, Range range) {
        this.myEditText = editText;
        this.myRange = range;
    }

    public boolean isValid() {
        String text = myEditText.getText().toString().trim();
        if (StringUtils.isTextEmpty(text) || Doubles.tryParse(text) == null)
            return false;

        Double value = Double.parseDouble(text);
        return value >= myRange.getLow() && value <= myRange.getHigh();
    }

}