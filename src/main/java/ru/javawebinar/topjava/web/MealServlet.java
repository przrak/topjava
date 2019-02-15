package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
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
    private static final String ADD_OR_EDIT_MEAL = "/addOrEditMeal.jsp";
    private static final String LIST_MEAL = "/meals.jsp";

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
                int mealId = Integer.parseInt(req.getParameter("mealId"));
                UserMeal userMeal = dao.get(mealId);
                req.setAttribute("userMeal", userMeal);
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
                List<UserMealWithExceed> userMealWithExceed = MealsUtil.getListWithExceed(this.dao.getAll());
                req.setAttribute("mealList", userMealWithExceed);
                req.getRequestDispatcher(forward).forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String mealId = req.getParameter("id");
        String datetime = req.getParameter("datetime");
        LocalDateTime localDateTime = LocalDateTime.parse(datetime);
        int calories = !req.getParameter("calories").isEmpty() ?
                Integer.parseInt(req.getParameter("calories")) : 0;

        UserMeal userMeal = new UserMeal(localDateTime,
            req.getParameter("description"), calories);

        if (mealId != null && !mealId.isEmpty())
        {
            userMeal.setId(Integer.parseInt(mealId));
            this.dao.update(userMeal);
        }
        else
        {
            this.dao.add(userMeal);
        }

        resp.sendRedirect("meals");
    }
}
