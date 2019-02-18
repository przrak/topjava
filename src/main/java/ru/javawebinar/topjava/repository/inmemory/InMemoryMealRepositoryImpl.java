package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        return repository.values().removeIf(meal -> (meal.getId() == id
                && meal.getUserId() == userId));
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        return repository.values()
                .stream()
                .filter(meal -> meal.getId() == id)
                .filter(meal -> meal.getUserId() == userId)
                .findFirst().orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll");
        return getStream(userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAllByDateTime(int userId, Map<String, String> filterData) {
        log.info("getAllByDate");

        return getStream(userId)
                .filter(meal -> (DateTimeUtil.isBetweenDate(meal.getDate(),
                        filterData.get("startDate"), filterData.get("endDate"))))
                .filter(meal -> (DateTimeUtil.isBetweenTime(meal.getTime(),
                        filterData.get("startTime"), filterData.get("endTime"))))
                .collect(Collectors.toList());
    }

    private Stream<Meal> getStream(int userId)
    {
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed());
    }
}

