package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final int USER_MEAL_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = START_SEQ + 3;

    public static final Meal USER_MEAL = new Meal(USER_MEAL_ID, LocalDateTime.of(2019, Month.FEBRUARY, 25, 23, 5), "Завтрак", 500);
    public static final Meal ADMIN_MEAL = new Meal(ADMIN_MEAL_ID, LocalDateTime.of(2019, Month.FEBRUARY, 25, 23, 4), "Ужин", 500);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
