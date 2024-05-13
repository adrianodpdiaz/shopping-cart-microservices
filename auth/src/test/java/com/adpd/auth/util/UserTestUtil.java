package com.adpd.auth.util;

import com.adpd.auth.entity.User;
import com.adpd.auth.resource.form.CreateUserForm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserTestUtil {

    private static final String TEST_EMAIL = "test@test.com";
    private static final String TEST_PASSWORD = "AAAbbbccc@123";

    public static User mockUser(Long id) {
        User user = new User();
        user.setId(id);
        user.setEmail(TEST_EMAIL);
        user.setPassword(TEST_PASSWORD);
        return user;
    }

    public static CreateUserForm mockCreateUserForm() {
        CreateUserForm createUserForm = new CreateUserForm();
        createUserForm.setEmail(TEST_EMAIL);
        createUserForm.setPassword(TEST_PASSWORD);
        return createUserForm;
    }
}
