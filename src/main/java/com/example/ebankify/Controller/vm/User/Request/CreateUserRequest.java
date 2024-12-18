package com.example.ebankify.Controller.vm.User.Request;

import com.example.ebankify.DTO.UserDTO;
import com.example.ebankify.Entity.Enums.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

        @Null
        private Long id;

        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        private String name;

        @NotBlank(message = "Email is required")
        @Email(message = "Please provide a valid email address")
        private String email;

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
                message = "Password must contain at least one digit, one lowercase, one uppercase letter and one special character")
        private String password;

        @NotNull(message = "Age is required")
        @Min(value = 18, message = "Age must be at least 18")
        private Integer age;


        private Double Monthly_income;

        @NotNull(message = "Credit score is required")
        @Min(value = 300, message = "Credit score must be at least 300")
        @Max(value = 850, message = "Credit score cannot exceed 850")
        private Integer creditScore;


        private Role role;

}
