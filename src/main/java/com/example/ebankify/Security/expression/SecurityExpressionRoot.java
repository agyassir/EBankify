package com.example.ebankify.Security.expression;



import com.example.ebankify.Config.SecurityUser;
import com.example.ebankify.Entity.Enums.Role;
import com.example.ebankify.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityExpressionRoot {

    protected User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((SecurityUser) authentication.getPrincipal()).user();
    }

    protected boolean isAdmin() {
        return getCurrentUser().getRole() == Role.ADMIN;
    }

    protected boolean isEmployee() {
        return getCurrentUser().getRole() == Role.EMPLOYEE;
    }
}
