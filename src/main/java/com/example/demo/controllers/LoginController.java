package com.example.demo.controllers;

import com.example.demo.auth.AuthenticationRequest;
import com.example.demo.auth.AuthenticationResponse;
import com.example.demo.auth.AuthenticationService;
import com.example.demo.dto.UserDTO;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.response.LoginResponse;
import com.example.demo.service.UserServiceImpl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/secure")
@RequiredArgsConstructor
public class LoginController
{

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private final AuthenticationService service;

    @PostMapping(path = "/register")
    public ResponseEntity<String> addUser(@RequestBody UserDTO request)
    {
        return service.register(request);
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> addUser(@RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping (path = "/login")
    public LoginResponse login(@RequestBody Map<String, String> json)
    {
        UserDTO userDTO  =  UserDTO.builder().email(json.get("email")).password(json.get("password")).build();
        return userService.login(userDTO);
    }

    @GetMapping(path = "/getUser")
    public User getUser(@RequestParam String email)
    {
        return userRepository.findByEmail(email);
    }
}
