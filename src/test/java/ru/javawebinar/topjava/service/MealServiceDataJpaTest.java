package ru.javawebinar.topjava.service;


import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.Meal;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
public class MealServiceDataJpaTest extends AbstractMealServiceTest {
    @Test
    public void getWithUserById() throws Exception {
        Meal meal = service.getWithUserById(ADMIN_MEAL_ID, ADMIN_ID);
        assertMatch(meal, ADMIN_MEAL1_WITH_USER);
        assertMatchUser(meal.getUser(), ADMIN_MEAL1_WITH_USER.getUser());
    }
}
