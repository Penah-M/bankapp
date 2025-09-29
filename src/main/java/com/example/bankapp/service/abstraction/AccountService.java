package com.example.bankapp.service.abstraction;

import com.example.bankapp.dto.request.AccountRequest;
import com.example.bankapp.dto.request.TransferRequest;
import com.example.bankapp.dto.response.AccountResponse;

import java.math.BigDecimal;

public interface AccountService {

    AccountResponse create(AccountRequest request);

    void deposit(Long accountId, BigDecimal deposit);

    void withdraw(Long accountId, BigDecimal withdraw );

    void transfer(TransferRequest transferRequest);

    BigDecimal show (Long accountId);


}
