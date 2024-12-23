package com.example.ebankify.Security.expression;


import com.example.ebankify.DTO.AccountDTO;
import com.example.ebankify.Entity.Account;
import com.example.ebankify.Service.AccountService;
import com.example.ebankify.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("accountSecurity")
@RequiredArgsConstructor
public class AccountSecurityExpression extends SecurityExpressionRoot {

    private final AccountService accountService;
    private final UserService userService;

    public boolean canAccessAccount(Long accountId) {
        AccountDTO account = accountService.getAccountById(accountId);
        return isAdmin() ||
                isEmployee() ||
                userService.getUserById(account.getUserId()).equals(getCurrentUser().getId());
    }

    public boolean canModifyAccount(Long accountId) {
        AccountDTO account = accountService.getAccountById(accountId);
        return isAdmin() ||  userService.getUserById(account.getUserId()).equals(getCurrentUser().getId());
    }

    public boolean canUpdateStatus(Long accountId) {
        return isAdmin();
    }

    public boolean canCreateAccount(Long userId) {
        return isAdmin() || getCurrentUser().getId().equals(userId);
    }

    public boolean canAccessUserAccounts(Long userId) {
        return isAdmin() ||
                isEmployee() ||
                getCurrentUser().getId().equals(userId);
    }
}
