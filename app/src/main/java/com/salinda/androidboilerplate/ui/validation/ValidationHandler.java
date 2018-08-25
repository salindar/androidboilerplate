package com.salinda.androidboilerplate.ui.validation;


import com.salinda.androidboilerplate.ui.validation.validator.BaseValidator;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salinda
 */
public class ValidationHandler {
    private List<BaseValidator> validatorList;

    @Inject
    public ValidationHandler() {
        if (validatorList == null) {
            validatorList = new ArrayList<>();
        }
    }

    public void addValidator(BaseValidator validator) {
        validatorList.add(validator);
    }

    public boolean validate() {
        boolean isValid = true;
        if (validatorList != null && validatorList.size() > 0) {
            for (BaseValidator baseValidator : validatorList) {
                boolean currentValidity = baseValidator.validate();
                if (!currentValidity) {
                    isValid = Boolean.FALSE;
                }
                if (!currentValidity) {
                    baseValidator.getEditText().setError(baseValidator.getErrorMessage());
                }else{
                    baseValidator.getEditText().setError(null);
                }
            }
        }
        return isValid;
    }
}
