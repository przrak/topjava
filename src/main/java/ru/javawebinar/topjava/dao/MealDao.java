package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

public interface MealDao {
    UserMeal add(UserMeal userMeal);
    UserMeal get(int mealId);
    UserMeal update(UserMeal userMeal);
    void delete(int mealId);
    List<UserMeal> getAll();
}
