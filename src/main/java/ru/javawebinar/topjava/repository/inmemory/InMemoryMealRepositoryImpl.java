package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository
{
    private final Logger log = LoggerFactory.getLogger(getClass());

    //Map useId -> (mealId, meal)
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(m -> this.save(m, 1));
    }

    @Override
    public Meal save(Meal meal, int userId)
    {
        log.info("save {}", meal);
        Map<Integer, Meal> mealMap;

        if (meal.isNew())
        {
            meal.setId(counter.incrementAndGet());
            mealMap = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
            mealMap.put(meal.getId(), meal);
            return meal;
        }

        // treat case: update, but absent in storage
        mealMap = repository.get(userId);
        if (mealMap == null)
        {
            return null;
        }

        return mealMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId)
    {
        log.info("delete {}", id);
        Map<Integer, Meal> mealMap = repository.get(userId);
        if (mealMap == null)
        {
            return false;
        }

        return mealMap.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId)
    {
        log.info("get {}", id);
        Map<Integer, Meal> mealMap = repository.get(userId);
        if (mealMap == null)
        {
            return null;
        }

        return mealMap.get(id);
    }

    @Override
    public List<Meal> getAll(int userId)
    {
        log.info("getAll");
        Map<Integer, Meal> mealMap = repository.get(userId);
        if (mealMap == null)
        {
            return new ArrayList<>();
        }

        return new ArrayList<>(mealMap.values());
    }
}

