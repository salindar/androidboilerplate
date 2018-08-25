package com.salinda.androidboilerplate.ui.validation.validator;

import android.widget.EditText;

/**
 * Created by Salinda
 */
public abstract class BaseValidator {
    public abstract boolean validate();

    public abstract String getErrorMessage();

    public abstract EditText getEditText();
}
