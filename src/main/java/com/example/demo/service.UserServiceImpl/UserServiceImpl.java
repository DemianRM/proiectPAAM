package com.example.demo.service.UserServiceImpl;

import com.example.demo.dto.UserDTO;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.response.LoginResponse;
import com.example.demo.transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse createUser(UserDTO userDTO) {

        User existingUser = userRepository.findByEmail(userDTO.getEmail());
        if(existingUser != null)
            return new LoginResponse(false, "Email Address already exist!");
        if(userDTO.getPassword().length() < 10)
            return new LoginResponse(false, "Password must have more the 10 characters");

        if(!userDTO.getEmail().contains("@"))
            return new LoginResponse(false, "Invalid email address");

        User user = UserTransformer.mapUserDTOtoUser(userDTO);

        userRepository.save(user);

        return new LoginResponse(true, "Account created!");
    }

    public LoginResponse login(UserDTO userDTO)
    {
        User searchedUser = userRepository.findByEmail(userDTO.getEmail());
        if(searchedUser != null)
        {
            if(passwordEncoder.matches(userDTO.getPassword(), searchedUser.getPassword()))
            {
                return new LoginResponse(true, "Login Success!");
            }
            else
            {
                return new LoginResponse(false, "Invalid password!");
            }
        }
        else
        {
            return new LoginResponse(false, "Email address doesn't exist");
        }
    }
}
