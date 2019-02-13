package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDaoImpl implements MealDao {

    private static final Logger log = getLogger(MealDaoImpl.class);
    private static final Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private static AtomicInteger id = new AtomicInteger();

    static {
        Meal meal1 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, generateId());
        Meal meal2 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, generateId());
        Meal meal3 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, generateId());
        Meal meal4 = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, generateId());
        Meal meal5 = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, generateId());
        Meal meal6 = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, generateId());

        meals.put(meal1.getId(), meal1);
        meals.put(meal2.getId(), meal2);
        meals.put(meal3.getId(), meal3);
        meals.put(meal4.getId(), meal4);
        meals.put(meal5.getId(), meal5);
        meals.put(meal6.getId(), meal6);
    }


    private static int generateId() {
        return id.getAndIncrement();
    }

    @Override
    public Meal add(Meal meal) {
        meal.setId(generateId());
        meals.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public Meal get(int mealId) {
        return meals.get(mealId);
    }

    @Override
    public Meal update(Meal meal) {
        meals.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int mealId) {
        meals.remove(mealId);
    }

    @Override
    public synchronized List<Meal> getAll() {
        Collection<Meal> c = meals.values();
        return new ArrayList<>(c);
    }
}
