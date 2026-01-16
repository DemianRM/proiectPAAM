package com.example.demo.service.UserServiceImpl;

import com.example.demo.dto.UserDTO;
import com.example.demo.response.LoginResponse;

public interface UserService{

    LoginResponse createUser(UserDTO userDTO);

    LoginResponse login(UserDTO userDTO);
}
