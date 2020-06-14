package com.durov.maks.cinema.validation;

import com.durov.maks.cinema.model.dto.user.UserRequestDto;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordsEqualsValidator
        implements ConstraintValidator<PasswordsEquals, UserRequestDto> {
    private String password;
    private String repeatPassword;

    public void initialize(PasswordsEquals constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.repeatPassword = constraintAnnotation.repeatPassword();
    }

    @Override
    public boolean isValid(UserRequestDto value, ConstraintValidatorContext context) {
        String pass = (String) new BeanWrapperImpl(value)
                .getPropertyValue(password);
        String repeatPass = (String) new BeanWrapperImpl(value)
                .getPropertyValue(repeatPassword);
        return Objects.equals(pass, repeatPass);
    }
}
