package ru.javawebinar.topjava.repository.jdbc.hsqldb;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.repository.jdbc.AbstractJdbcMealRepository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static ru.javawebinar.topjava.Profiles.HSQL_DB;

@Repository
@Profile({HSQL_DB})
public class JdbcMealRepositoryHSQLDBImpl extends AbstractJdbcMealRepository<Timestamp> {

    @Override protected Timestamp convertDateTime(LocalDateTime ldc)
    {
        return Timestamp.valueOf(ldc);
    }
}
