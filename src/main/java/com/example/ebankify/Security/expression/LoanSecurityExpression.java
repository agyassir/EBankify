package com.example.ebankify.Security.expression;


import com.example.ebankify.DTO.AccountDTO;
import com.example.ebankify.Entity.Account;
import com.example.ebankify.Service.AccountService;
import com.example.ebankify.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("loanSecurity")
@RequiredArgsConstructor
public class LoanSecurityExpression extends SecurityExpressionRoot {

    private final AccountService accountService;
    private final UserService userService;


    public boolean canAccessLoan(Long loanId) {
        AccountDTO account = accountService.getAccountById(loanId);
        return isAdmin() ||
                isEmployee() ||
                userService.getUserById(account.getUserId()).equals(getCurrentUser().getId());
    }

    public boolean canApproveLoan() {
        return isAdmin() || isEmployee();
    }

    public boolean canApplyForLoan() {
        return true; // All authenticated users can apply
    }
}
