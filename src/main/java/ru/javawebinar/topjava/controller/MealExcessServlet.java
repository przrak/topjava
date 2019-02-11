package ru.javawebinar.topjava.controller;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class MealExcessServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MealDao dao;


    public MealExcessServlet() {
        dao = new MealDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MealTo> mealTo = MealsUtil.getListWithExcess(this.dao.getAllMeals(), MealDao.getCaloriesPerDay());
        req.setAttribute("list", mealTo);
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }
}
