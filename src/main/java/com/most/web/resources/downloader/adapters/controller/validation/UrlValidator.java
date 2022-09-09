package com.most.web.resources.downloader.adapters.controller.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class UrlValidator implements ConstraintValidator<URL, String> {

    private final String[] schemes = {"http", "https"};
    private final org.apache.commons.validator.routines.UrlValidator validator = new org.apache.commons.validator.routines.UrlValidator(schemes);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validator.isValid(value);
    }
}
