package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMealUtils {

    public static List<UserMealWithExceed> getWithExceed(Collection<UserMeal> mealList, int caloriesPerDay) {
       return getFilteredWithExceed(mealList, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    public static List<UserMealWithExceed> getFilteredWithExceed(Collection<UserMeal> UserMeals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = UserMeals.stream()
                .collect(
                        Collectors.groupingBy(UserMeal::getDate, Collectors.summingInt(UserMeal::getCalories))
//                      Collectors.toMap(UserMeal::getDate, UserMeal::getCalories, Integer::sum)
                );

        return UserMeals.stream()
                .filter(UserMeal -> TimeUtil.isBetween(UserMeal.getTime(), startTime, endTime))
                .map(UserMeal -> createWithExceed(UserMeal, caloriesSumByDate.get(UserMeal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static UserMealWithExceed createWithExceed(UserMeal userUserMeal, boolean Exceed) {
        return new UserMealWithExceed(userUserMeal.getId(), userUserMeal.getDateTime(), userUserMeal.getDescription(), userUserMeal.getCalories(), Exceed);
    }
}