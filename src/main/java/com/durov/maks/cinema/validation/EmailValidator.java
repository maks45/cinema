package com.durov.maks.cinema.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidate, String> {
   public void initialize(EmailValidate constraint) {

   }

   public boolean isValid(String email, ConstraintValidatorContext context) {
      return email.matches("[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,}");
   }
}
