package com.bookstore.dto;

import com.bookstore.validation.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@FieldMatch(
        first = "password",
        second = "repeatPassword",
        message = "Passwords must match"
)
@Getter
@Setter
public class UserRegistrationRequestDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 255,
            message = "Password must be between 8 and 255 characters")
    private String password;

    @NotBlank(message = "Repeat password is required")
    @Size(min = 8, max = 255,
            message = "Repeat password must be between 8 and 255 characters")
    private String repeatPassword;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    private String shippingAddress;
}
