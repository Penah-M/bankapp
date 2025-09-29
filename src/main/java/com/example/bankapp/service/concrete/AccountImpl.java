package com.example.bankapp.service.concrete;

import com.example.bankapp.dao.entity.AccountEntity;
import com.example.bankapp.dao.entity.UserEntity;
import com.example.bankapp.dao.repository.AccountRepository;
import com.example.bankapp.dao.repository.UserRepository;
import com.example.bankapp.dto.request.AccountRequest;
import com.example.bankapp.dto.request.TransferRequest;
import com.example.bankapp.dto.response.AccountResponse;
import com.example.bankapp.enums.AccountStatus;
import com.example.bankapp.exception.AlreadyExistsException;
import com.example.bankapp.exception.InsufficientBalanceException;
import com.example.bankapp.exception.NotFoundException;
import com.example.bankapp.service.abstraction.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AccountImpl implements AccountService {
    AccountRepository accountRepository;
    UserRepository userRepository;


    @Override
    public AccountResponse create(AccountRequest request) {
        log.info("Account hesabi yaranmaga basladi");

        UserEntity userEntity = userRepository.findById(request.getUserId()).orElseThrow(() -> {

            log.error("Id uyğun istifadeçi tapilmadi: {}", request.getUserId());

            return new NotFoundException("Id tapilmai");
        });


        if (accountRepository.findByUserId(request.getUserId()).isPresent()) {
            log.error("Bu istifadeci artiq movcuddur");
            throw new AlreadyExistsException("Bu account yaradilib");
        }
        AccountEntity accountEntity = AccountEntity.builder()

                .balance(request.getBalance())
                .status(AccountStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .userId(request.getUserId())
                .build();
        accountRepository.save(accountEntity);


        log.info("Account yaradildi..");

        return AccountResponse.builder()
                .balance(accountEntity.getBalance())
                .createdAt(accountEntity.getCreatedAt())
                .id(accountEntity.getId())
                .status(accountEntity.getStatus())
                .userId(accountEntity.getUserId())
                .build();
    }

    @Override
    public void deposit(Long accountId, BigDecimal deposit) {
        log.info("Depozit emliyyati baslayir...");

        AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(() -> {
            log.error("Bu id: {} movcud deyil", accountId);
            return new NotFoundException("Bu id aid hesab tapilmadi");
        });

        accountEntity.setBalance(accountEntity.getBalance().add(deposit));
        accountEntity.setUpdatedAt(LocalDateTime.now());
        log.info("Depozit emliyyati sona catdi balansina {} AZN yuklendi", deposit);
        accountRepository.save(accountEntity);


    }

    @Override
    public void withdraw(Long userId, BigDecimal withdraw) {
        log.info("Witdraw emliyyati baslayir...");

        AccountEntity accountEntity = accountRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("Bu ID-li {} istifadeci yoxdur", userId);
                    return new NotFoundException("Bu idli istifadeci yoxdur");
                });
        if (accountEntity.getBalance().compareTo(withdraw) < 0) {
            log.error("Balansinizda kifayet qeder vesait yoxdur:{}", accountEntity.getBalance());
            throw new InsufficientBalanceException("Kifayet qeder vesait yoxdur");
        }

        log.info("Witdraw emliyyati icra oldu...");
        accountEntity.setBalance(accountEntity.getBalance().subtract(withdraw));
        accountEntity.setUpdatedAt(LocalDateTime.now());
        accountRepository.save(accountEntity);


    }

    @Override
    @Transactional
    public void transfer(TransferRequest transferRequest) {
        log.info("transfer bu {} ile {} arasinda baslayr", transferRequest.getFromId(), transferRequest.getToId());
        withdraw(transferRequest.getFromId(), transferRequest.getAmount());

        deposit(transferRequest.getToId(), transferRequest.getAmount());


        }

    @Override
    public BigDecimal show(Long accountId) {
        AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(() -> {
            log.error("Bu id: {} movcud deyil", accountId);
            return new NotFoundException("Bu id aid hesab tapilmadi");});
       return accountEntity.getBalance();


}}



