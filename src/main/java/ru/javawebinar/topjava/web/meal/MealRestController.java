package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController
{
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    @Autowired public MealRestController(MealService service)
    {
        this.service = service;
    }

    public Meal create(Meal meal)
    {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, authUserId());
    }

    public void delete(int id)
    {
        log.info("delete {}", id);
        service.delete(id, authUserId());
    }

    public Meal get(int id)
    {
        log.info("get {}", id);
        return service.get(id, authUserId());
    }

    public void update(Meal meal, int id)
    {
        log.info("update {}", meal);
        assureIdConsistent(meal, id);
        service.update(meal, authUserId());
    }

    public List<MealTo> getAll()
    {
        log.info("getAll with userId={}", authUserId());
        return service.getAll(authUserId());
    }

    public List<MealTo> getAllByDateTime(String startDate, String endDate,
                                         String startTime, String endTime)
    {

        LocalDate sd = !startDate.isEmpty() ?
            LocalDate.parse(startDate, DateTimeUtil.DATE_FORMATTER) : LocalDate.MIN;
        LocalDate ed = !endDate.isEmpty() ?
            LocalDate.parse(endDate, DateTimeUtil.DATE_FORMATTER) : LocalDate.MAX;
        LocalTime st = !startTime.isEmpty() ?
            LocalTime.parse(startTime, DateTimeUtil.TIME_FORMATTER) : LocalTime.MIN;
        LocalTime et = !endTime.isEmpty() ?
            LocalTime.parse(endTime, DateTimeUtil.TIME_FORMATTER) : LocalTime.MAX;


        log.info("getAll with userId={}", authUserId());
        return service.getAllByDateTime(authUserId(), sd, ed, st, et);
    }
}