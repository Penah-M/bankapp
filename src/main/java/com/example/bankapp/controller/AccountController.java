package com.example.bankapp.controller;


import com.example.bankapp.dto.request.AccountRequest;
import com.example.bankapp.dto.response.AccountResponse;
import com.example.bankapp.service.abstraction.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("v1/account")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE,makeFinal = true)
public class AccountController {

    AccountService accountService;

    @PostMapping("create")
    AccountResponse create(@RequestBody AccountRequest request){
        return accountService.create(request);
    }


     @PostMapping("{accountId}/deposit")
    void deposit(@PathVariable Long accountId, @RequestParam BigDecimal deposit){
        accountService.deposit(accountId,deposit);
     }
    @PostMapping("{accountId}/withdraw")
    void withdraw(@PathVariable Long accountId, @RequestParam BigDecimal withdraw ){
        accountService.withdraw(accountId,withdraw);
    }

}
