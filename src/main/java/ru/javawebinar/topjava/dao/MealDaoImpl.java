package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao {

    private final Map<Integer, UserMeal> meals = new ConcurrentHashMap<>();
    private AtomicInteger id = new AtomicInteger();

    public MealDaoImpl() {
        UserMeal userMeal1 = new UserMeal(generateId(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        UserMeal userMeal2 = new UserMeal(generateId(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
        UserMeal userMeal3 = new UserMeal(generateId(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
        UserMeal userMeal4 = new UserMeal(generateId(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
        UserMeal userMeal5 = new UserMeal(generateId(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
        UserMeal userMeal6 = new UserMeal(generateId(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);

        meals.put(userMeal1.getId(), userMeal1);
        meals.put(userMeal2.getId(), userMeal2);
        meals.put(userMeal3.getId(), userMeal3);
        meals.put(userMeal4.getId(), userMeal4);
        meals.put(userMeal5.getId(), userMeal5);
        meals.put(userMeal6.getId(), userMeal6);
    }

    private int generateId() {
        return id.getAndIncrement();
    }

    @Override
    public UserMeal add(UserMeal userMeal) {
        userMeal.setId(generateId());
        meals.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public UserMeal get(int mealId) {
        return meals.get(mealId);
    }

    @Override
    public UserMeal update(UserMeal userMeal) {
        meals.replace(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public void delete(int mealId) {
        meals.remove(mealId);
    }

    @Override
    public List<UserMeal> getAll() {
        Collection<UserMeal> c = meals.values();
        return new ArrayList<>(c);
    }
}
