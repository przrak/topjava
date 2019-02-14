package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao {

    private final Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private AtomicInteger id = new AtomicInteger();

    public MealDaoImpl() {
        Meal meal1 = new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        Meal meal2 = new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
        Meal meal3 = new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
        Meal meal4 = new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
        Meal meal5 = new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
        Meal meal6 = new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);

        meals.put(meal1.getId(), meal1);
        meals.put(meal2.getId(), meal2);
        meals.put(meal3.getId(), meal3);
        meals.put(meal4.getId(), meal4);
        meals.put(meal5.getId(), meal5);
        meals.put(meal6.getId(), meal6);
    }

    private int generateId() {
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
        meals.replace(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int mealId) {
        meals.remove(mealId);
    }

    @Override
    public List<Meal> getAll() {
        Collection<Meal> c = meals.values();
        return new ArrayList<>(c);
    }
}
