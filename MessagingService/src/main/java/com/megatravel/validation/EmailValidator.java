package com.megatravel.validation;

import java.util.regex.Matcher;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation, String>{
	@Override
	public boolean isValid(String customField, ConstraintValidatorContext ctx) {
		
		if(customField == null) {
			return false;
		}
		Matcher matcher = StaticData.VALID_EMAIL_ADDRESS_REGEX.matcher(customField);
        return matcher.find();
	}

	@Override
	public void initialize(EmailValidation arg0) {		
	}
}