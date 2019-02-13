package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ADD_OR_EDIT_MEAL = "/addOrEditMeal.jsp";
    private static final String LIST_MEAL = "/meals.jsp";

    private static final Logger log = getLogger(MealServlet.class);

    private MealDao dao;

    @Override
    public void init() throws ServletException {
        this.dao = new MealDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward;
        String action = req.getParameter("action") != null ?
            req.getParameter("action") : "";

        switch (action)
        {
            case "delete":
            {
                int mealId = Integer.parseInt(req.getParameter("mealId"));
                dao.delete(mealId);
                resp.sendRedirect("meals");
                break;
            }
            case "edit":
            {
                forward = ADD_OR_EDIT_MEAL;
                int mealId = Integer.parseInt(req.getParameter("mealId"));
                Meal meal = dao.get(mealId);
                req.setAttribute("meal", meal);
                req.getRequestDispatcher(forward).forward(req, resp);
                break;
            }
            case "add":
            {
                forward = ADD_OR_EDIT_MEAL;
                req.getRequestDispatcher(forward).forward(req, resp);
                break;
            }
            default:
            {
                forward = LIST_MEAL;
                List<MealTo> mealTo = MealsUtil.getListWithExcess(this.dao.getAll());
                req.setAttribute("meals", mealTo);
                req.getRequestDispatcher(forward).forward(req, resp);
            }
        }
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
                this.dao.update(meal);
            }
            else
            {
                this.dao.add(meal);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }

        resp.sendRedirect("meals");
    }
}