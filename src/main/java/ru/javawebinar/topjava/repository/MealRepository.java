package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MealRepository {
    Meal save(Meal meal, int userId);

    boolean delete(int id, int userId);

    Meal get(int id, int userId);

    List<MealTo> getAll(int userId);

    List<MealTo> getAllByDateTime(int userId, LocalDate startDate, LocalDate endDate,
                                LocalTime startTime, LocalTime endTime);
}
