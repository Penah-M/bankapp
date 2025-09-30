package com.example.bankapp.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class TransferRequest {

    @Positive(message = "fromId musbet reqem olmalidir")
    Long fromId;

    @Positive(message = "toId musbet reqem olmalidir")
    Long toId;

    @NotNull(message = "Bos ve menfi(-) ola bilmez")
    BigDecimal amount;
}

