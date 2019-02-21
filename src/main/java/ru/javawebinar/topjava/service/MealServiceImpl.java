package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService
{

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public Meal create(Meal meal, int userId)
    {
        return checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException
    {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException
    {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void update(Meal meal, int userId)
    {
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    @Override
    public List<MealTo> getAll(int userId)
    {
        return MealsUtil.getFilteredWithExcess(repository.getAll(userId),
            MealsUtil.DEFAULT_CALORIES_PER_DAY, meal -> true);
    }

    @Override
    public List<MealTo> getAllByDateTime(int userId, LocalDate startDate, LocalDate endDate,
                                         LocalTime startTime, LocalTime endTime)
    {
        return MealsUtil.getFilteredWithExcess(repository.getAll(userId),
            MealsUtil.DEFAULT_CALORIES_PER_DAY, meal -> (DateTimeUtil.isBetween(meal.getDate(), startDate, endDate)))
            .stream().filter(meal -> (DateTimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)))
            .collect(Collectors.toList());

    }

}