package com.example.bankapp.dto.response;


import com.example.bankapp.enums.AccountStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {


    Long id;

    Long userId;

    BigDecimal balance;


    AccountStatus status;


    LocalDateTime createdAt;


    LocalDateTime updatedAt;




}

