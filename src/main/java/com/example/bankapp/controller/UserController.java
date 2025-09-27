package com.example.bankapp.controller;


import com.example.bankapp.dto.request.UserRequest;
import com.example.bankapp.dto.response.UserResponse;
import com.example.bankapp.service.abstraction.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping("create")
    UserResponse create(@Valid @RequestBody UserRequest request) {
        return userService.create(request);
    }

    @GetMapping("allusers")
    List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("allactive")
    List<UserResponse> getAllActiveStatus() {
        return userService.getAllActiveStatus();
    }

    @GetMapping("alldelete")
    List<UserResponse> getAllDeleteStatus() {
        return userService.getAllDeleteStatus();
    }

    @GetMapping("allblock")
    List<UserResponse> getAllBlockStatus() {
        return userService.getAllBlockStatus();
    }

    @PutMapping("{id}/delete")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

    }

    @PutMapping("{id}/update")
    UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        return userService.updateUser(id, request);
    }

    @PutMapping("{id}/block")
    void blockUser(@PathVariable Long id) {
        userService.blockUser(id);
    }

    @PutMapping("{id}/active")
    void activeUser(@PathVariable Long id) {
        userService.activeUser(id);

    }
    @DeleteMapping("deleteall")
    public void deleteAllUsers(){
        userService.deleteAllUsers();
    }



}
