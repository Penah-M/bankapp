package com.example.bankapp.dao.entity;


import com.example.bankapp.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "accounts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    Long userId;

    BigDecimal balance;

    @Enumerated(EnumType.STRING)
    AccountStatus status;


    @CreationTimestamp
    LocalDateTime createdAt;


    @UpdateTimestamp
    LocalDateTime updatedAt;

}
