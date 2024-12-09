package com.example.ebankify.Service.Implementation;

import com.example.ebankify.Config.JWTService;
import com.example.ebankify.Controller.vm.Auth.AuthenticationResponse;
import com.example.ebankify.Controller.vm.Auth.LoginRequest;
import com.example.ebankify.Controller.vm.User.Request.CreateUserRequest;
import com.example.ebankify.DTO.UserDTO;
import com.example.ebankify.Entity.User;
import com.example.ebankify.Repository.UserRepository;
import com.example.ebankify.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final ModelMapper mapper;
    @Override
    public UserDTO register(CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already registered");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .age(request.getAge())
                .monthlyIncome(request.getMonthlyIncome())
                .creditScore(request.getCreditScore())
                .build();

        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDTO.class);
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        return null;
    }

}
