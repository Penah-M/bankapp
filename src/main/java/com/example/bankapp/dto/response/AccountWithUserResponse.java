package com.example.bankapp.dto.response;


import com.example.bankapp.enums.AccountStatus;
import com.example.bankapp.enums.UserStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AccountWithUserResponse {
    Long id;

    String name;

    String surname;

    String email;


    Long userId;

    BigDecimal balance;

    AccountStatus status;

    LocalDateTime createdAt;

    LocalDateTime uptadeAt;


}
