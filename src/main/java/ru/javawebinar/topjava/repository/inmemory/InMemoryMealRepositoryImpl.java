package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
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
        MealsUtil.MEALS.forEach(m -> this.save(m, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        Map<Integer, Meal> mealMap;

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            mealMap = repository.computeIfAbsent(userId, m -> new HashMap<>());
            mealMap.put(meal.getId(), meal);
            repository.put(userId, mealMap);
            return meal;
        }

        // treat case: update, but absent in storage
        mealMap = repository.get(userId);
        if (mealMap == null)
            return null;

        return mealMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        Map<Integer, Meal> mealMap = repository.get(userId);
        if (mealMap == null)
            return false;

        return mealMap.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        Map<Integer, Meal> mealMap = repository.get(userId);
        if (mealMap == null)
            return null;

        return mealMap.get(id);
    }

    @Override
    public List<MealTo> getAll(int userId) {
        log.info("getAll");
        return getFiltered(userId, meal -> true);
    }

    @Override
    public List<MealTo> getAllByDateTime(int userId, LocalDate startDate, LocalDate endDate,
                                       LocalTime startTime, LocalTime endTime) {
        log.info("getAllByDate");

        return getFiltered(userId, meal -> (DateTimeUtil.isBetween(meal.getDate(), startDate, endDate)))
                .stream().filter(meal -> (DateTimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)))
                .collect(Collectors.toList());
    }

    private List<MealTo> getFiltered(int userId, Predicate<Meal> filter)
    {
        Map<Integer, Meal> mealMap = repository.get(userId);
        if (mealMap == null)
            return new ArrayList<>();

        return MealsUtil.getFilteredWithExcess(mealMap.values(), MealsUtil.DEFAULT_CALORIES_PER_DAY, filter);
    }
}

