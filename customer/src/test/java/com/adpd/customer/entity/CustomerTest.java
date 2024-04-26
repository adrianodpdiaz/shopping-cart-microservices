package com.adpd.customer.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.adpd.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class CustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldSaveAndFindCustomer() {
        LocalDate birthDate = LocalDate.of(1990, 2, 15);

        Customer customer = new Customer();
        customer.setFirstName("First");
        customer.setLastName("Last");
        customer.setEmail("example@example.com");
        customer.setBirthDate(birthDate);

        customerRepository.save(customer);
        Long id = customer.getId();
        Customer savedCustomer = customerRepository.findById(id).orElseThrow();

        assertEquals(id, savedCustomer.getId());
        assertEquals("First", savedCustomer.getFirstName());
        assertEquals("Last", savedCustomer.getLastName());
        assertEquals("example@example.com", savedCustomer.getEmail());
        assertEquals(birthDate, savedCustomer.getBirthDate());
    }
}
