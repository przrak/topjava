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
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        log.info("save {}", meal);
        Map<Integer, Meal> mealMap;
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            if (repository.get(meal.getUserId()) != null)
                mealMap = repository.get(meal.getUserId());
            else
                mealMap = new HashMap<>();
            mealMap.put(meal.getId(), meal);
            repository.put(meal.getUserId(), mealMap);
            return meal;
        }

        // treat case: update, but absent in storage
        mealMap = repository.get(meal.getUserId());
        return mealMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        Map<Integer, Meal> mealMap = repository.get(userId);
        return mealMap.values().removeIf(meal -> (meal.getId() == id));
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        Map<Integer, Meal> mealMap = repository.get(userId);
        return mealMap.values()
                .stream()
                .filter(meal -> meal.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll");
        return getFiltered(userId, meal -> true, meal -> true);
    }

    @Override
    public List<Meal> getAllByDateTime(int userId, LocalDate startDate, LocalDate endDate,
                                       LocalTime startTime, LocalTime endTime) {
        log.info("getAllByDate");
        return getFiltered(userId,
                meal -> (DateTimeUtil.isBetween(meal.getDate(), startDate, endDate)),
                meal -> (DateTimeUtil.isBetween(meal.getTime(), startTime, endTime)));
    }

    private List<Meal> getFiltered(int userId, Predicate<Meal> filterByDate, Predicate<Meal> filterByTime) {
        log.info("getFiltered");
        Map<Integer, Meal> mealMap = repository.get(userId);
        if (mealMap == null)
            return new ArrayList<>();

        return mealMap.values().stream()
                .filter(filterByDate)
                .filter(filterByTime)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

