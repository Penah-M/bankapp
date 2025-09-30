package com.example.bankapp.service.concrete;

import com.example.bankapp.dao.entity.UserEntity;
import com.example.bankapp.dao.repository.UserRepository;
import com.example.bankapp.dto.request.UserRequest;
import com.example.bankapp.dto.response.UserResponse;
import com.example.bankapp.enums.UserStatus;
import com.example.bankapp.exception.AlreadyActiveException;
import com.example.bankapp.exception.AlreadyBlockException;
import com.example.bankapp.exception.AlreadyExistsException;
import com.example.bankapp.exception.NotFoundException;
import com.example.bankapp.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;


@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserImpl implements UserService {

    UserRepository userRepository;

    @Override
    public UserResponse create(UserRequest request) {
        log.info("Start create user: {}", request.getEmail());
        boolean present = userRepository.findByEmail(request.getEmail()).isPresent();

        if (present) {
            log.error("Bu mail artiq istifade olunub: {}", request.getEmail());
            throw new AlreadyExistsException("Bu mail artiq istifade olunub" + request.getEmail());
        }
        UserEntity userEntity = UserEntity.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .status(UserStatus.ACTIVE)
                .build();
        userRepository.save(userEntity);

        log.info("Istifadeci yaradildi");
        return UserResponse.builder()
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .createdAt(userEntity.getCreatedAt())
                .email(userEntity.getEmail())
                .id(userEntity.getId())
                .status(userEntity.getStatus())
                .build();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        log.info("Butun istifadeciler gosterilecek");
        List<UserEntity> entities = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();

        for (UserEntity entity : entities) {
            UserResponse userResponse = UserResponse.builder()
                    .status(entity.getStatus())
                    .id(entity.getId())
                    .email(entity.getEmail())
                    .name(entity.getName())
                    .surname(entity.getSurname())
                    .createdAt(entity.getCreatedAt())
                    .build();
            userResponses.add(userResponse);
        }
        log.info("Butun istifadeciler gosterildi");
        return userResponses;
    }


    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        log.info("Bu ID-e sahib istifadeci update olur: {}", id);
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Id-e uygun istifadeci tapilmadi" + id));

        userEntity.setName(request.getName());
        userEntity.setSurname(request.getSurname());
        userEntity.setEmail(request.getEmail());
        userEntity.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userEntity);

        log.info("Bu ID-e sahib istifadeci update oldu:{}", id);
        return UserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .email(userEntity.getEmail())
                .status(userEntity.getStatus())
                .updatedAt(LocalDateTime.now())
                .createdAt(userEntity.getCreatedAt())
                .build();

    }

    @Override
    public void blockUser(Long id) {
        log.info("Istifadecinin Statusunun Block olmasi baslayir: {}", id);
        UserEntity userEntity = userRepository.findById(id).
                orElseThrow(() -> {
                    log.error("Id uyğun istifadeçi tapilmadi: {}", id);
                    return new  NotFoundException("Id uygun melumat tapilmadi");
                });
        if (userEntity.getStatus().equals(UserStatus.BLOCK)) {
            log.error("Bu ID-e sahib istifadecinin statusu BLOCK-dur: {}", id);
            throw new AlreadyBlockException("Daxil etdiyiniz istifadecinin statusu blokdur");
        }
        log.info("Istifadecinin Statusunun Block oldu: {}", id);
        userEntity.setStatus(UserStatus.BLOCK);
        userEntity.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userEntity);

    }

    @Override
    public void activeUser(Long id) {
        log.info("Istifadecinin Statusunun ACTIVE olmasi baslayir: {}", id);
        UserEntity userEntity = userRepository.findById(id).
                orElseThrow(() ->{
                    log.error("Id uyğun istifadeçi tapilmadi: {}", id);
                     return new  NotFoundException("Id uygun melumat tapilmadi");

                });

        if (userEntity.getStatus().equals(UserStatus.ACTIVE)) {
            log.error("Bu ID:  {} sahib istifadecinin statusu ACTIVE-dir", id);
            throw new AlreadyActiveException("Daxil etdiyiniz istifadecinin statusu aktivdir");
        }

        log.info("Istifadecinin Statusu ACTIVE oldu: {}", id);

        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userEntity);

    }


    @Override
    public void deleteUser(Long id) {
        log.info("Istifadecinin Statusunun DELETE olmasi baslayir: {}", id);
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Id uyğun istifadeçi tapilmadi: {}", id);

                   return new NotFoundException("Id uygun melumat tapolmadi");
                });

        log.info("Istifadecinin Statusu DELETE oldu: {}", id);
        userEntity.setStatus(UserStatus.DELETE);
        userEntity.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userEntity);


    }


    @Override
    public List<UserResponse> getAllActiveStatus() {
        log.info("ACTIVE statuslu istifadeciler getirilmeye basladi");
        List<UserEntity> entities = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();


        for (UserEntity entity : entities) {
            if (entity.getStatus().equals(UserStatus.ACTIVE)) {
                UserResponse userResponse = UserResponse.builder()
                        .status(entity.getStatus())
                        .id(entity.getId())
                        .email(entity.getEmail())
                        .name(entity.getName())
                        .surname(entity.getSurname())
                        .createdAt(entity.getCreatedAt())
                        .build();
                userResponses.add(userResponse);
            }


        }
        log.info("ACTIVE statuslu {} istifadeçi tapildi", userResponses.size());
        return userResponses;
    }


    @Override
    public List<UserResponse> getAllDeleteStatus() {
        log.info("DELETE statuslu istifadeciler getirilmeye basladi");
        List<UserEntity> entities = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();

        for (UserEntity entity : entities) {
            if (entity.getStatus().equals(UserStatus.BLOCK)) {
                UserResponse userResponse = UserResponse.builder()
                        .status(entity.getStatus())
                        .id(entity.getId())
                        .email(entity.getEmail())
                        .name(entity.getName())
                        .surname(entity.getSurname())
                        .createdAt(entity.getCreatedAt())
                        .build();
                userResponses.add(userResponse);
            }
        }
        log.info("DELETE statuslu {} istifadeçi tapildi", userResponses.size());
        return userResponses;
    }


    @Override
    public List<UserResponse> getAllBlockStatus() {
        log.info("BLOCK statuslu istifadeciler getirilmeye basladi");

        List<UserEntity> entities = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();


        for (UserEntity entity : entities) {
            if (entity.getStatus().equals(UserStatus.BLOCK)) {
                UserResponse userResponse = UserResponse.builder()
                        .status(entity.getStatus())
                        .id(entity.getId())
                        .email(entity.getEmail())
                        .name(entity.getName())
                        .surname(entity.getSurname())
                        .createdAt(entity.getCreatedAt())
                        .build();
                userResponses.add(userResponse);
            }
        }
        log.info("BLOCK statuslu {} istifadeçi tapildi", userResponses.size());
        return userResponses;
    }

    @Override
    public void deleteAllUsers() {
        log.info("Bütün istifadeciler silinmeye basladi");

        userRepository.deleteAll();
        log.info("Bütün istifadeciler silindi");

    }

    @Override
    public UserResponse finByUsr(Long userId) {
        log.info("Istifadecinin Melumatlari hazirlanir: {}", userId);
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("Id uyğun istifadeçi tapilmadi: {}", userId);

                    return new NotFoundException("Id -e uygun melumat tapilmadi");
                });
        return  UserResponse.builder()
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .build();

    }




}

