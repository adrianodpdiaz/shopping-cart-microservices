package com.adpd.auth.entity;

import com.adpd.auth.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static com.adpd.auth.util.UserTestUtil.mockUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class UserTest {

    private Validator validator;

    @Inject
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void shouldSaveAndFindUser() {
        User mockUser = mockUser(null);

        userRepository.save(mockUser);
        Long id = mockUser.getId();
        User user = userRepository.findById(id).orElseThrow();

        assertEquals(id, user.getId());
        assertEquals(mockUser.getEmail(), user.getEmail());
        assertEquals(mockUser.getPassword(), user.getPassword());
    }

    @Test
    void shouldInvalidateNullEmail() {
        User user = mockUser(null);
        user.setEmail(null);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(1, violations.size());
        for (ConstraintViolation<User> violation : violations) {
            assertEquals("e-mail column cannot be null", violation.getMessage());
        }
    }
}
