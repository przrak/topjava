package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    Meal add(Meal meal);
    Meal get(int mealId);
    Meal update(Meal meal);
    void delete(int mealId);
    List<Meal> getAll();
}
