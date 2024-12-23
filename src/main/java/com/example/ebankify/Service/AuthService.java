package com.example.ebankify.Service;

import com.example.ebankify.Controller.vm.Auth.AuthenticationResponse;
import com.example.ebankify.Controller.vm.Auth.LoginRequest;
import com.example.ebankify.Controller.vm.User.Request.CreateUserRequest;
import com.example.ebankify.DTO.UserDTO;

public interface AuthService {
    UserDTO register(CreateUserRequest request);
    AuthenticationResponse login(LoginRequest request);
}
