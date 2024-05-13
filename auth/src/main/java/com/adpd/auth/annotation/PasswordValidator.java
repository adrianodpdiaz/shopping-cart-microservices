package com.adpd.auth.annotation;

import org.passay.*;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if(password == null ) {
            return false;
        }

        org.passay.PasswordValidator passwordValidator = new org.passay.PasswordValidator(Arrays.asList(
            //Length rule. Min 10 max 128 characters
            new LengthRule(10, 25),
            //At least one upper case letter
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            //At least one lower case letter
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            //At least one number
            new CharacterRule(EnglishCharacterData.Digit, 1),
            //At least one special characters
            new CharacterRule(EnglishCharacterData.Special, 1),
            new WhitespaceRule()
        ));

        RuleResult result = passwordValidator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }

        //Sending one message each time failed validation
        constraintValidatorContext.buildConstraintViolationWithTemplate(
            passwordValidator.getMessages(result).stream().findFirst().get())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
