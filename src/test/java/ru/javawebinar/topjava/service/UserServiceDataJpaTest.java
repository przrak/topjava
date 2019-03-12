package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.User;

import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(DATAJPA)
public class UserServiceDataJpaTest extends AbstractUserServiceTest {

    @Test
    public void getWithMealById() throws Exception {
        User user = service.getWithMealById(ADMIN_ID);
        assertMatch(user, ADMIN_WITH_MEAL);
        assertMatchMeal(user.getMeals(), ADMIN_WITH_MEAL.getMeals());
    }
}
