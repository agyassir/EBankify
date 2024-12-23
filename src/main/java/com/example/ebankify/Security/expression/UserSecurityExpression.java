package com.example.ebankify.Security.expression;


import com.example.ebankify.Entity.Enums.Role;
import com.example.ebankify.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("userSecurity")
@RequiredArgsConstructor
public class UserSecurityExpression extends SecurityExpressionRoot {

    public boolean canAccessUserData(Long userId) {
        User currentUser = getCurrentUser();
        return currentUser.getRole() == Role.ADMIN ||
                currentUser.getRole() == Role.EMPLOYEE ||
                currentUser.getId().equals(userId);
    }

    public boolean canModifyUser(Long userId) {
        User currentUser = getCurrentUser();
        return currentUser.getRole() == Role.ADMIN ||
                currentUser.getId().equals(userId);
    }
}
