package com.example.bankapp.dto.response;

import com.example.bankapp.enums.UserStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserResponse {

    Long id;

    String name;

    String surname;

    UserStatus status;

    String email;


    LocalDateTime createdAt;

    LocalDateTime updatedAt;

}
