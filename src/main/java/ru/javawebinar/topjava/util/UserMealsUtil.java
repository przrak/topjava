package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> filteredMealsWithExceed = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredMealsWithExceed.forEach(System.out::println);


    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime,
                                                                   LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> caloriesSumPerDay = new HashMap<>();

        //my variant
        for (UserMeal meal : mealList) {
            LocalDate mealDate = meal.getDateTime().toLocalDate();
            caloriesSumPerDay.merge(mealDate, meal.getCalories(), Integer::sum);
        }

        List<UserMealWithExceed> mealExceeded = new ArrayList<>();

        mealList.forEach(meal -> {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                mealExceeded.add(new UserMealWithExceed(
                        meal.getDateTime(),
                        meal.getDescription(),
                        meal.getCalories(),
                        caloriesSumPerDay.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        });

        //teacher variant

//        for (UserMeal meal : mealList)
//        {
//            LocalDate mealDate = meal.getDateTime().toLocalDate();
//            caloriesSumPerDay.put(mealDate, caloriesSumPerDay.getOrDefault(mealDate, 0) + meal.getCalories());
//        }
//
//        List<UserMealWithExceed> mealExceeded = new ArrayList<>();
//
//        for (UserMeal meal : mealList)
//        {
//            LocalDateTime dateTime = meal.getDateTime();
//            if (TimeUtil.isBetween(dateTime.toLocalTime(), startTime, endTime))
//            {
//                mealExceeded.add(new UserMealWithExceed(
//                        meal.getDateTime(),
//                        meal.getDescription(),
//                        meal.getCalories(),
//                        caloriesSumPerDay.get(dateTime.toLocalDate()) > caloriesPerDay));
//            }
//        }

        return mealExceeded;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededStreamAPI(List<UserMeal> mealList, LocalTime startTime,
                                                                            LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream()
                .collect(Collectors.groupingBy(userMeal -> userMeal.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> new UserMealWithExceed(um.getDateTime(), um.getDescription(),
                        um.getCalories(), caloriesSumByDate.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<UserMealWithExceed> getFilteredWithExceededStreamAPIOneReturn(List<UserMeal> mealList, LocalTime startTime,
                                                                                     LocalTime endTime, int caloriesPerDay) {
        return mealList.stream()
                .collect(Collectors.groupingBy(UserMeal::getDateTime)).values().stream()
                .flatMap(dayMeals -> {
                    boolean exceed = dayMeals.stream()
                            .mapToInt(UserMeal::getCalories).sum() > caloriesPerDay;
                    return dayMeals.stream()
                            .filter(meal ->
                                    TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                            .map(meal -> createWithExceed(meal, exceed));
                })
                .collect(Collectors.toList());
    }

    public static UserMealWithExceed createWithExceed(UserMeal meal, boolean exceed) {
        return new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceed);
    }

    public static List<UserMealWithExceed> getFilteredWithExceededStreamAPIOnePassCollector(List<UserMeal> mealList, LocalTime startTime,
                                                                                            LocalTime endTime, int caloriesPerDay) {

        final class Aggregate {
            private final List<UserMeal> dailyMeals = new ArrayList<>();
            private int dailySumOfCalories;

            private void accumulate(UserMeal userMeal) {
                dailySumOfCalories += userMeal.getCalories();
                if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                    dailyMeals.add(userMeal);
            }

            //never invoked if the upstream is sequential
            private Aggregate combine(Aggregate that) {
                this.dailySumOfCalories += that.dailySumOfCalories;
                this.dailyMeals.addAll(that.dailyMeals);
                return this;
            }

            private Stream<UserMealWithExceed> finisher() {
                final boolean exceed = dailySumOfCalories > caloriesPerDay;
                return mealList.stream().map(userMeal -> createWithExceed(userMeal, exceed));
            }
        }

        Collection<Stream<UserMealWithExceed>> values = mealList.stream()
                .collect(Collectors.groupingBy(UserMeal::getDateTime, Collector.of(Aggregate::new,
                        Aggregate::accumulate, Aggregate::combine, Aggregate::finisher))).values();

        return values.stream().flatMap(Function.identity()).collect(Collectors.toList());

    }
}
