package com.example.auth.service;

import com.example.auth.dto.UserDTO;
import com.example.auth.dto.UserRequest;
import com.example.auth.entity.Role;
import com.example.auth.entity.User;
import com.example.auth.exception.UserExistException;
import com.example.auth.exception.UserNotFoundException;
import com.example.auth.mapper.UserMapper;
import com.example.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;

    @Override
    public UserDTO register(UserRequest userRequest) {
        if (repository.existsByUsername(userRequest.getUserName())) {
            throw new UserExistException(userRequest.getUserName());
        }

        User user = new User();
        user.setFullName(userRequest.getFullName());
        user.setUsername(userRequest.getUserName());
        user.setPassword(encoder.encode(userRequest.getPassword()));
        user.setRole(Role.USER);

        return mapper.toDTO(repository.save(user));
    }

    @Override
    public UserDTO findByUserName(String userName) {
        return repository.findByUsername(userName)
                .map(mapper::toDTO).orElseThrow(() -> new UserNotFoundException(userName));
    }

    @Override
    public UserDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = repository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }
        return users.stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}
