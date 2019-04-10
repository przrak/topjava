package ru.javawebinar.topjava.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

class AdminUIControllerTest extends AbstractControllerTest
{
    private static final String REST_URL = AdminRestController.REST_URL + '/';

    @Test
    void testUpdate() throws Exception
    {
        User updated = new User(USER);
        updated.setEnabled(true);
        updated.setRoles(Collections.singletonList(Role.ROLE_USER));
        mockMvc.perform(get(REST_URL + USER_ID + "?enabled=true")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertMatch(userService.get(USER_ID), updated);
    }
}