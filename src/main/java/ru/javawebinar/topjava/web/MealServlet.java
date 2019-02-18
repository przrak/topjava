package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private MealRestController mealRestController;
    private ClassPathXmlApplicationContext springContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(springContext.getBeanDefinitionNames()));
        mealRestController = springContext.getBean(MealRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        //значит это фильтрация
        if (id == null)
        {
            log.info("Фильтрация");
            Map<String, String> filterMap = new HashMap<>();
            filterMap.put("startDate", request.getParameter("startDate"));
            filterMap.put("endDate", request.getParameter("endDate"));
            filterMap.put("startTime", request.getParameter("startTime"));
            filterMap.put("endTime", request.getParameter("endTime"));
            HttpSession session = request.getSession(false);
            if (request.getParameter("reset") == null)
            {
                session.setAttribute("filterData", filterMap);
            }
            else
            {
                session.setAttribute("filterData", null);
            }
        }
        else
        {
            Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("calories")), 1);

            log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
            mealRestController.create(meal);
        }
        response.sendRedirect("meals");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                mealRestController.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000, 1) :
                        mealRestController.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
//                Object startDate = request.getAttribute("startDate");
//                Object endDate = request.getAttribute("endDate");
//                Object startTime = request.getAttribute("startTime");
//                Object endTime = request.getAttribute("endTime");

                if (request.getSession().getAttribute("filterData") != null)
                {
                    HashMap map = (HashMap) request.getSession().getAttribute("filterData");
                    request.setAttribute("startDate", map.get("startDate"));
                    request.setAttribute("endDate", map.get("endDate"));
                    request.setAttribute("startTime", map.get("startTime"));
                    request.setAttribute("endTime", map.get("endTime"));
                    request.setAttribute("meals", mealRestController.getAllByDateTime(map));
                }
                else
                {
                    request.setAttribute("meals", mealRestController.getAll());
                }

                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
