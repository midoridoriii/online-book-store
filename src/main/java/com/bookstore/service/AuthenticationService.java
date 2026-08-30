package com.bookstore.service;

import com.bookstore.dto.UserLoginRequestDto;
import com.bookstore.dto.UserLoginResponseDto;
import com.bookstore.dto.UserRegistrationRequestDto;
import com.bookstore.dto.UserResponseDto;
import com.bookstore.exception.RegistrationException;

public interface AuthenticationService {
    UserResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException;

    UserLoginResponseDto login(UserLoginRequestDto request);
}
