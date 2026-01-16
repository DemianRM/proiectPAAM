package com.example.demo.transformers;

import com.example.demo.dto.UserDTO;
import com.example.demo.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserTransformer {

    public static UserDTO mapUserToUserDTO(User user)
    {
        UserDTO userDTO = UserDTO.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getFirstName())
                .build();
        return userDTO;
    }

    public static  User mapUserDTOtoUser(UserDTO userDTO)
    {
        User user = User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getFirstName())
                .build();
        return user;
    }
}
