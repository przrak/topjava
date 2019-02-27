package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app-common.xml",
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal meal = new Meal(null, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Полдник", 1000);
        Meal created = service.create(meal, ADMIN_ID);
        meal.setId(created.getId());
        assertMatch(service.getAll(ADMIN_ID), ADMIN_MEAL_3, ADMIN_MEAL_2, ADMIN_MEAL_1, meal);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateDateCreate() throws Exception {
        service.create(new Meal(null, LocalDateTime.of(2019, Month.FEBRUARY, 25, 14, 13), "Другой Обед", 700), USER_ID);
    }

    @Test
    public void delete() {
        service.delete(ADMIN_MEAL_ID_2, ADMIN_ID);
        assertMatch(service.getAll(ADMIN_ID), ADMIN_MEAL_3, ADMIN_MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        service.delete(USER_MEAL_ID_2, ADMIN_ID);
    }

    @Test
    public void get() {
        Meal meal = service.get(USER_MEAL_ID_1, USER_ID);
        assertMatch(meal, USER_MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(ADMIN_MEAL_ID_3, USER_ID);
    }

    @Test
    public void getBetweenDateTimes() {
        LocalDateTime startDateTime = LocalDateTime.of(2019, Month.FEBRUARY, 24, 23, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2019, Month.FEBRUARY, 25, 23, 10);
        List<Meal> filteredFromBD = service.getBetweenDateTimes(startDateTime, endDateTime, USER_ID);
        assertMatch(filteredFromBD, USER_MEAL_2);
    }

    @Test
    public void update() {
        Meal updated = new Meal(ADMIN_MEAL_1);
        updated.setDescription("Updated Admin Meal");
        updated.setCalories(240);
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(ADMIN_MEAL_ID_1, ADMIN_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        Meal updated = new Meal(ADMIN_MEAL_1);
        service.update(updated, USER_ID);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, USER_MEAL_3, USER_MEAL_2, USER_MEAL_1);
    }
}