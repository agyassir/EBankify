package com.example.ebankify.Entity;


import com.example.ebankify.Entity.Account;
import com.example.ebankify.Entity.Enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true, nullable = false)
    @NotBlank
    @Email
    private String email;

    @Column(nullable = false)
    private String name;

    @NotBlank
    private String password;

    @NotNull
    @Min(18)
    private Integer age;

    @NotNull
    @Positive
    private Double monthly_income;

    @NotNull
    @Min(300)
    @Max(850)
    private Integer creditScore;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Account> accounts = new ArrayList<>();
}