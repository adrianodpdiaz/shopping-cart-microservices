package com.adpd.auth.service;

import com.adpd.auth.entity.User;
import com.adpd.auth.mapping.UserMapper;
import com.adpd.auth.repository.UserRepository;
import com.adpd.auth.resource.form.CreateUserForm;
import com.adpd.auth.resource.dto.UserDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public Long createUser(CreateUserForm createUserForm) {
        User newUser = userMapper.requestToEntity(createUserForm);
        newUser.setPassword(passwordEncoder.encode(createUserForm.getPassword()));
        userRepository.saveAndFlush(newUser);
        return newUser.getId();
    }

    public UserDetailsDTO getUser(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                String.format("User with email: %s not found", email)));
        return userMapper.toDTO(user);
    }
}
