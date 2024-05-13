package com.adpd.customer.entity;

import com.adpd.customer.repository.CustomerRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static com.adpd.customer.util.CustomerTestUtil.mockCustomer;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class CustomerTest {

    private Validator validator;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void shouldSaveAndFindCustomer() {
        Customer mockCustomer = mockCustomer(1L);

        customerRepository.save(mockCustomer);
        Long id = mockCustomer.getId();
        Customer customer = customerRepository.findById(id).orElseThrow();

        assertEquals(id, customer.getId());
        assertEquals(mockCustomer.getFirstName(), customer.getFirstName());
        assertEquals(mockCustomer.getLastName(), customer.getLastName());
        assertEquals(mockCustomer.getEmail(), customer.getEmail());
        assertEquals(mockCustomer.getBirthDate(), customer.getBirthDate());
    }

    @Test
    void shouldInvalidateNullEmail() {
        Customer customer = mockCustomer(null);
        customer.setEmail(null);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertEquals(1, violations.size());
        for (ConstraintViolation<Customer> violation : violations) {
            assertEquals("e-mail column cannot be null", violation.getMessage());
        }
    }
}
