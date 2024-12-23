package com.example.ebankify.Controller;

import com.example.ebankify.DTO.AccountDTO;
import com.example.ebankify.Service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/accounts")
@Tag(name = "Account Management", description = "APIs for managing bank accounts")
@PreAuthorize("isAuthenticated()")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Create a new account
    @Operation(summary = "Create new account", description = "Creates a new bank account for a user")
    @ApiResponse(responseCode = "200", description = "Account created successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @PreAuthorize("@accountSecurity.canCreateAccount(#accountDTO.userId)")
    @PostMapping("/")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountDTO accountDTO) {
       return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id) {
        AccountDTO account = accountService.getAccountById(id);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Retrieve all accounts
    @Operation(summary = "Get all accounts", description = "Retrieves all accounts in the system")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // Update an account
    @Operation(summary = "Update account", description = "Updates account details")
    @PreAuthorize("@accountSecurity.canModifyAccount(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        AccountDTO updatedAccount = accountService.updateAccount(id, accountDTO);
        if (updatedAccount != null) {
            return ResponseEntity.ok(updatedAccount);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete an account
    @Operation(summary = "Delete account", description = "Deletes an account from the system")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        boolean isDeleted = accountService.deleteAccount(id);

                return ResponseEntity.noContent().build();

    }
}
