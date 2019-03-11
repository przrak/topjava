package ru.javawebinar.topjava.service;


import org.springframework.test.context.ActiveProfiles;

import static ru.javawebinar.topjava.Profiles.JDBC;
import static ru.javawebinar.topjava.Profiles.POSTGRES_DB;

@ActiveProfiles({JDBC, POSTGRES_DB})
public class MealServiceJdbcPostgresTest extends AbstractMealServiceTest {
}
