package com.example.auth.mapper;

import com.example.auth.dto.UserDTO;
import com.example.auth.entity.Role;
import com.example.auth.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-30T19:13:25+0500",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setFullName( user.getFullName() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );
        if ( user.getRole() != null ) {
            userDTO.setRole( user.getRole().name() );
        }

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setFullName( dto.getFullName() );
        user.setUsername( dto.getUsername() );
        user.setPassword( dto.getPassword() );
        if ( dto.getRole() != null ) {
            user.setRole( Enum.valueOf( Role.class, dto.getRole() ) );
        }

        return user;
    }
}
