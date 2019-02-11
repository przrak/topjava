package ru.javawebinar.topjava.controller;

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
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ADD_OR_EDIT_MEAL = "/addOrEditMeal.jsp";
    private static final String LIST_MEAL = "/listMeal.jsp";

    private MealDao dao;

    public MealServlet() {
        this.dao = new MealDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action") != null ? req.getParameter("action") : "listMeal";

        if (action.equalsIgnoreCase("delete"))
        {
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            dao.deleteMealById(mealId);
            forward = LIST_MEAL;
            List<MealTo> mealTo = MealsUtil.getListWithExcess(this.dao.getAllMeals(),
                    MealDao.getCaloriesPerDay());
            req.setAttribute("meals", mealTo);
        }
        else if (action.equalsIgnoreCase("edit"))
        {
            forward = ADD_OR_EDIT_MEAL;
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            Meal meal = dao.getMealById(mealId);
            req.setAttribute("meal", meal);
        }
        else if (action.equalsIgnoreCase("listMeal")) {
            forward = LIST_MEAL;
            List<MealTo> mealTo = MealsUtil.getListWithExcess(this.dao.getAllMeals(),
                    MealDao.getCaloriesPerDay());
            req.setAttribute("meals", mealTo);
        }
        else
        {
            forward = ADD_OR_EDIT_MEAL;
        }

        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Meal meal = new Meal(req.getParameter("localDateTime"), req.getParameter("description"),
//                req.getParameter("calories"), this.dao.generateId());
//        meal.setDateTime(req.getParameter("firstName"));
//        user.setLastName(request.getParameter("lastName"));
//        try {
//            Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("dob"));
//            user.setDob(dob);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        user.setEmail(request.getParameter("email"));
//        String userid = request.getParameter("userid");
//        if(userid == null || userid.isEmpty())
//        {
//            dao.addUser(user);
//        }
//        else
//        {
//            user.setUserid(Integer.parseInt(userid));
//            dao.updateUser(user);
//        }
        RequestDispatcher view = req.getRequestDispatcher(LIST_MEAL);
        List<MealTo> mealTo = MealsUtil.getListWithExcess(this.dao.getAllMeals(),
                MealDao.getCaloriesPerDay());
        req.setAttribute("meals", mealTo);
        view.forward(req, resp);
    }
}
