package com.younghun.library.config;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
    @Size(min = 2, max = 20)
    @NotEmpty(message="Username is Necessary")
    private String username;

    @NotEmpty(message="Password is Necessary")
    private String password1;

    @NotEmpty(message="Password Confirmation is Necessary")
    private String password2;

    @Email
    @NotEmpty(message="Email is Necessary")
    private String email;
}
