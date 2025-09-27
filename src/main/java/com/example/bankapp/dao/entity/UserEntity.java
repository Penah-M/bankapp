package com.example.bankapp.dao.entity;


import com.example.bankapp.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.PRIVATE;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@FieldDefaults(level = PRIVATE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;


    @Column(nullable = false)
    String name;


    @Column(nullable = false)
    String surname;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    UserStatus status;

    @Column(nullable = false,unique = true)
    String email;


//    @CreatedDate

    @CreationTimestamp
    LocalDateTime createdAt;


//    @LastModifiedDate
    @UpdateTimestamp
    LocalDateTime updatedAt;


}
