package com.adpd.auth.service;

import com.adpd.auth.entity.User;
import com.adpd.auth.mapping.UserMapper;
import com.adpd.auth.repository.UserRepository;
import com.adpd.auth.resource.form.CreateUserForm;
import com.adpd.feignclients.customer.resource.dto.UserDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Long createUser(CreateUserForm createUserForm) {
        User newUser = userMapper.requestToEntity(createUserForm);
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
