package com.example.bankapp.service.concrete;

import com.example.bankapp.dao.entity.TransactionEntity;
import com.example.bankapp.dao.repository.TransactionRepository;
import com.example.bankapp.dto.request.TransferRequest;
import com.example.bankapp.enums.TransactionType;
import com.example.bankapp.service.abstraction.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE,makeFinal = true)
public class TransactionImpl implements TransactionService {

    TransactionRepository transactionRepository;


    @Override
    public void depositLog(Long toId, BigDecimal amount) {
        TransactionEntity transactionEntity= TransactionEntity.builder()
                .amount(amount)
                .type(TransactionType.DEPOSIT)
                .toId(toId)
                .createdAt(LocalDateTime.now())
                .build();
        transactionRepository.save(transactionEntity);


    }

    @Override
    public void withdrawLog(Long fromId, BigDecimal amount) {
        TransactionEntity transactionEntity= TransactionEntity.builder()
                .amount(amount)
                .type(TransactionType.WITHDRAW)
                .fromId(fromId)
                .createdAt(LocalDateTime.now())
                .build();
        transactionRepository.save(transactionEntity);

    }

    @Override
    public void transferLog(TransferRequest request) {
        TransactionEntity transactionEntity= TransactionEntity.builder()
                .amount(request.getAmount())
                .type(TransactionType.TRANSFER)
                .toId(request.getToId())
                .fromId(request.getFromId())
                .createdAt(LocalDateTime.now())
                .build();
        transactionRepository.save(transactionEntity);

    }
}
