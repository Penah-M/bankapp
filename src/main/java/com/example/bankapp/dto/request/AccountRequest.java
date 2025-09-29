package com.example.bankapp.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;



@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class AccountRequest {


    @NotNull(message = "Balans bos ve menfi(-) ola bilmez")
    BigDecimal balance;

    @NotNull(message = "User id cannot be null")
    Long userId;


}


