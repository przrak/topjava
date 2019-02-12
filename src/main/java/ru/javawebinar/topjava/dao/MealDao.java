package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    void add(Meal meal);
    void deleteById(int mealId);
    void update(Meal meal);
    List<Meal> getAll();
    Meal getById(int mealId);

    int generateId();
    static int getCaloriesPerDay()
    {
        return 2000;
    }
}
