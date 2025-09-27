package com.example.bankapp.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    @NotBlank(message = "Ad boş ola bilməz")
    String name;

    @NotBlank(message = "Soyad boş ola bilməz")
    String surname;


    @NotBlank(message = "Email boş ola bilməz")
    @Email(message = "Duzgun email ünvani deyil")
    String email;


}
