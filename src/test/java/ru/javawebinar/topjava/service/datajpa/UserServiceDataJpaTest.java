package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import java.time.Month;
import java.util.List;

import static java.time.LocalDateTime.of;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(DATAJPA)
public class UserServiceDataJpaTest extends AbstractUserServiceTest
{

    @Test
    public void getWithMealById() throws Exception {
        User user = service.getWithMealById(ADMIN_ID);
        assertMatch(user, ADMIN);
        MealTestData.assertMatch(user.getMeals(), List.of(
            new Meal(100009, of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500),
            new Meal(100008, of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510)
        ));
    }
}
