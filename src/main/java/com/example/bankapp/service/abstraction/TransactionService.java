package com.example.bankapp.service.abstraction;

import com.example.bankapp.dto.request.TransferRequest;

import java.math.BigDecimal;

public interface TransactionService {

    void depositLog (Long toId, BigDecimal amount);
    void withdrawLog(Long fromId,BigDecimal amount);
    void transferLog(TransferRequest request);
}
