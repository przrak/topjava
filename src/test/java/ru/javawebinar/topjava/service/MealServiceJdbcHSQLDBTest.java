package ru.javawebinar.topjava.service;


import org.springframework.test.context.ActiveProfiles;

import static ru.javawebinar.topjava.Profiles.HSQL_DB;
import static ru.javawebinar.topjava.Profiles.JDBC;

@ActiveProfiles({JDBC, HSQL_DB})
public class MealServiceJdbcHSQLDBTest extends AbstractMealServiceTest {
}
