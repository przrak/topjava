package ru.javawebinar.topjava.repository.jdbc.postgres;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.repository.jdbc.AbstractJdbcMealRepository;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.Profiles.POSTGRES_DB;

@Repository
@Profile({POSTGRES_DB})
public class JdbcMealRepositoryPostgresImpl extends AbstractJdbcMealRepository<LocalDateTime> {

    @Override protected LocalDateTime convertDateTime(LocalDateTime ldc)
    {
        return ldc;
    }
}
