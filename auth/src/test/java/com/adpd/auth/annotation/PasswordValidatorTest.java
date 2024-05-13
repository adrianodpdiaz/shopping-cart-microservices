package com.adpd.auth.annotation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class PasswordValidatorTest {

    @InjectMocks
    private PasswordValidator passwordValidator;
    @Mock
    private ConstraintValidatorContext context;
    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder;

    @BeforeEach
    void setUp() {
        lenient().when(violationBuilder.addConstraintViolation()).thenReturn(context);
        lenient().when(context.buildConstraintViolationWithTemplate(any())).thenReturn(violationBuilder);
    }

    @ParameterizedTest(name = "#{index} - Test password = {0}")
    @MethodSource("validPasswordProvider")
    void testValidPassword(String password) {
        assertTrue(passwordValidator.isValid(password, context));
    }

    @ParameterizedTest(name = "#{index} - Test password = {0}")
    @MethodSource("invalidPasswordProvider")
    void testInvalidPassword(String password) {
        assertFalse(passwordValidator.isValid(password, context));
    }

    static Stream<String> validPasswordProvider() {
        return Stream.of(
            "AAAbbbccc@123",
            "Helloworld$123",
            "A!@#&()–a1",
            "A[{}]:;',?/*a1",
            "A~$^+=<>a1",
            "0123456789$abcdefgABaa45!",
            "123Aa$Aa.Aa"
        );
    }

    static Stream<String> invalidPasswordProvider() {
        return Stream.of(
            "1234567891",           // only digits
            "abcdefghif",                   // only lowercase
            "ABCDEFGHIF",                   // only uppercase
            "abc123$$$.",                   // no uppercase
            "ABC123$$$.",                   // no lowercase
            "ABC$$$$$$.",                   // no digits
            "javaREGEX123",                 // no special character
            "java123@",                     // too short
            "javaREGEX123%abcdefghi1234",   // too long
            "java REGEX123",                // whitespace
            "");                            // empty
    }
}
