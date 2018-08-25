package com.salinda.androidboilerplate.ui.validation.validator;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by Salinda
 */
public class EmptyFieldValidator extends BaseValidator {
    private EditText editText;
    private String errorMessage;

    public EmptyFieldValidator(EditText editText, String errorMessage) {
        this.editText = editText;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public EditText getEditText() {
        return editText;
    }

    @Override
    public boolean validate() {
        if (editText != null && !TextUtils.isEmpty(editText.getText())) {
            return true;
        }
        return false;
    }
}
