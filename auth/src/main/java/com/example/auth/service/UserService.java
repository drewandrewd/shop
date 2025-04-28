package com.example.auth.service;

import com.example.auth.dto.UserDTO;
import com.example.auth.dto.UserRequest;

import java.util.List;

public interface UserService {

    UserDTO register(UserRequest userRequest);
    UserDTO findByUserName(String userName);
    UserDTO getById(Long id);
    List<UserDTO> getAllUsers();
}
