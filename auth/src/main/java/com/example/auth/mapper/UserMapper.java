package com.example.auth.mapper;

import com.example.auth.dto.UserDTO;
import com.example.auth.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
    User toEntity(UserDTO dto);
}
