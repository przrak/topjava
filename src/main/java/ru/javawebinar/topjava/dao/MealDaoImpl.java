package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDaoImpl implements MealDao {
    
    private static final Logger log = getLogger(MealDaoImpl.class);

    private static int id = 0;

    private final List<Meal> meals = new ArrayList<>(Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, generateId()),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, generateId()),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, generateId()),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, generateId()),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, generateId()),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, generateId())
    ));

    public synchronized int generateId()
    {
        return id++;
    }

    @Override
    public void add(Meal meal) {
        try {
            synchronized (meals)
            {
                meal.setId(generateId());
                meals.add(meal);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void deleteById(int mealId) {
        try {
            synchronized (meals)
            {
                meals.removeIf(m -> mealId == m.getId());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void update(Meal meal) {
        try {
            synchronized (meals)
            {
                meals.stream()
                        .filter(m -> m.getId() == meal.getId())
                        .findFirst()
                        .ifPresent(oldMeal -> meals.set(meals.indexOf(oldMeal), meal));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public synchronized List<Meal> getAll() {
        return meals;
    }

    @Override
    public Meal getById(int mealId) {
        try {
            synchronized (meals)
            {
                return meals.stream().filter(m -> m.getId() == mealId).findFirst().orElse(null);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return null;
        }
    }
}
