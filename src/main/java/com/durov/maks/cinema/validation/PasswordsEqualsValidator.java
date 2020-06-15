package com.durov.maks.cinema.validation;

import com.durov.maks.cinema.model.dto.user.UserRequestDto;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsEqualsValidator
        implements ConstraintValidator<PasswordsEquals, UserRequestDto> {

    public void initialize() {
    }

    @Override
    public boolean isValid(UserRequestDto value, ConstraintValidatorContext context) {
        return Objects.equals(value.getPassword(), value.getRepeatPassword());
    }
}
