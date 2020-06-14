package com.durov.maks.cinema.model.dto.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.durov.maks.cinema.validation.EmailValidate;
import com.durov.maks.cinema.validation.PasswordsEquals;


@PasswordsEquals(
        password = "password",
        repeatPassword = "repeatPassword"
)
public class UserRequestDto {
    @EmailValidate(message = "email invalid")
    @NotNull(message = "email can't be null")
    private String email;
    @NotNull(message = "login can't be null")
    @Size(min = 3, message = "login size should be 3 or more symbols")
    private String login;
    @NotNull(message = "password can't be null")
    @Size(min = 4, message = "password length should be 4 or more symbols")
    private String password;
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
