package com.adpd.auth.mapping;

import com.adpd.auth.entity.User;
import com.adpd.auth.resource.form.CreateUserForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.adpd.auth.util.UserTestUtil.mockCreateUserForm;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @InjectMocks
    private UserMapperImpl userMapper;

    @Test
    void testNullMappings() {
        User user = userMapper.requestToEntity(null);
        assertNull(user);
    }

    @Test
    void testMappingFormToEntity() {
        CreateUserForm registerCustomerForm = mockCreateUserForm();

        User user = userMapper.requestToEntity(registerCustomerForm);

        assertEquals(registerCustomerForm.getEmail(), user.getEmail());
        assertEquals(registerCustomerForm.getPassword(), user.getPassword());
    }
}
