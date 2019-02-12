package ru.javawebinar.topjava.controller;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ADD_OR_EDIT_MEAL = "/addOrEditMeal.jsp";
    private static final String LIST_MEAL = "/listMeal.jsp";

    private static final Logger log = getLogger(MealServlet.class);

    private MealDao dao;

    public MealServlet() {
        this.dao = new MealDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward;
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";

        switch (action)
        {
            case "delete":
            {
                forward = LIST_MEAL;
                int mealId = Integer.parseInt(req.getParameter("mealId"));
                dao.deleteMealById(mealId);
                List<MealTo> mealTo = MealsUtil.getListWithExcess(this.dao.getAllMeals(),
                        MealDao.getCaloriesPerDay());
                req.setAttribute("meals", mealTo);
                break;
            }
            case "edit":
            {
                forward = ADD_OR_EDIT_MEAL;
                int mealId = Integer.parseInt(req.getParameter("mealId"));
                Meal meal = dao.getMealById(mealId);
                req.setAttribute("meal", meal);
                break;
            }
            case "add":
            {
                forward = ADD_OR_EDIT_MEAL;
                break;
            }
            default:
            {
                forward = LIST_MEAL;
                List<MealTo> mealTo = MealsUtil.getListWithExcess(this.dao.getAllMeals(),
                        MealDao.getCaloriesPerDay());
                req.setAttribute("meals", mealTo);
            }
        }

        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try
        {
            String mealId = req.getParameter("id");
            String datetime = req.getParameter("datetime");
            LocalDateTime localDateTime = LocalDateTime.parse(datetime);
            int calories = !req.getParameter("calories").isEmpty() ?
                    Integer.parseInt(req.getParameter("calories")) : 0;

            Meal meal = new Meal();
            meal.setDateTime(localDateTime);
            meal.setDescription(req.getParameter("description"));
            meal.setCalories(calories);

            if (mealId != null && !mealId.isEmpty())
            {
                meal.setId(Integer.parseInt(mealId));
                this.dao.updateMeal(meal);
            }
            else
            {
                meal.setId(this.dao.generateId());
                this.dao.addMeal(meal);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }

        RequestDispatcher view = req.getRequestDispatcher(LIST_MEAL);
        List<MealTo> mealTo = MealsUtil.getListWithExcess(this.dao.getAllMeals(),
                MealDao.getCaloriesPerDay());
        req.setAttribute("meals", mealTo);
        view.forward(req, resp);
    }
}
