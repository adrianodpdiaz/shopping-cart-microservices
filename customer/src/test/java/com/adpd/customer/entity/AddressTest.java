package com.adpd.customer.entity;

import com.adpd.customer.repository.AddressRepository;
import com.adpd.customer.util.CustomerTestParent;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class AddressTest extends CustomerTestParent {

    private Validator validator;

    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    public void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void shouldSaveAndFindAddress() {
        Address address = mockAddress(1L);

        addressRepository.save(address);
        Long id = address.getId();
        Address addressCreated = addressRepository.findById(id).orElseThrow();

        assertEquals(id, addressCreated.getId());
        assertEquals(address.getStreetAddress(), addressCreated.getStreetAddress());
        assertEquals(address.getCity(), addressCreated.getCity());
        assertEquals(address.getState(), addressCreated.getState());
        assertEquals(address.getCountry(), addressCreated.getCountry());
        assertEquals(address.getZipCode(), addressCreated.getZipCode());
    }

    @Test
    void shouldInvalidateNullEmail() {
        Address address = mockAddress(null);
        address.setZipCode(null);

        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        assertEquals(1, violations.size());
        for (ConstraintViolation<Address> violation : violations) {
            assertEquals("zip-code column cannot be null", violation.getMessage());
        }
    }
}
