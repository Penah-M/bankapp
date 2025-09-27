package com.example.bankapp.dao.repository;

import com.example.bankapp.dao.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity,Long> {

    Optional<AccountEntity> findByUserId(Long userId);
}
