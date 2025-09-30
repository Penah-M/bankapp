package com.example.bankapp.service.abstraction;

import com.example.bankapp.dto.request.UserRequest;
import com.example.bankapp.dto.response.UserResponse;

import java.util.List;

public interface UserService {


    UserResponse create(UserRequest request);
    List<UserResponse> getAllUsers ();
    List<UserResponse> getAllActiveStatus();
    List<UserResponse> getAllDeleteStatus();
    void deleteUser(Long id);
    UserResponse updateUser(Long id,UserRequest request);
    void blockUser(Long id);
    void activeUser(Long id);
    List<UserResponse> getAllBlockStatus();
   void deleteAllUsers();
    UserResponse finByUsr(Long userId);
}
