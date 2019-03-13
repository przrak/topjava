package ru.javawebinar.topjava.repository.jdbc.hsqldb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.jdbc.AbstractJdbcMealRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.Profiles.HSQL_DB;
import static ru.javawebinar.topjava.Profiles.JDBC;

@Repository
@Profile({HSQL_DB})
public class JdbcMealRepositoryHSQLDBImpl extends AbstractJdbcMealRepository {

    @Autowired
    public JdbcMealRepositoryHSQLDBImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override protected <T> T convertDateTime(T ldc)
    {
        return (T) Timestamp.valueOf((LocalDateTime) ldc);
    }
}
