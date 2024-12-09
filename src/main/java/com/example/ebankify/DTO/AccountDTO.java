package com.example.ebankify.DTO;

import com.example.ebankify.Entity.Enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private long id;
    private Double balance;
    private AccountStatus status;
    private long userId;


}
