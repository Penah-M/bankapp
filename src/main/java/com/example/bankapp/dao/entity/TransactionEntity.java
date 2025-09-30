package com.example.bankapp.dao.entity;

import com.example.bankapp.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "transaction_logs")
@FieldDefaults(level = PRIVATE)
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long fromId;

    Long toId;

    @Enumerated(EnumType.STRING)
    TransactionType type;

    BigDecimal amount;

    @CreationTimestamp
    LocalDateTime createdAt;
}
