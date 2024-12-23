package com.example.ebankify.Controller.vm.Auth;


import com.example.ebankify.Entity.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private Long userId;
    private String email;
    private Role role;
    private String token;
    private String refreshToken;
}
