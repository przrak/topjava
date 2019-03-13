package ru.javawebinar.topjava.repository.jdbc.postgres;

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

import static ru.javawebinar.topjava.Profiles.POSTGRES_DB;
import static ru.javawebinar.topjava.Profiles.JDBC;


@Repository
@Profile({POSTGRES_DB})
public class JdbcMealRepositoryPostgresImpl extends AbstractJdbcMealRepository {

    @Autowired
    public JdbcMealRepositoryPostgresImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }
}
