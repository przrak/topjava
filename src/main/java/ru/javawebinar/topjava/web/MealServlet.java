package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
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
            LocalDate startDate = request.getParameter("startDate") != null ?
                LocalDate.parse(request.getParameter("startDate"), DateTimeUtil.DATE_FORMATTER) : null;
            LocalDate endDate = request.getParameter("endDate") != null ?
                LocalDate.parse(request.getParameter("endDate"), DateTimeUtil.DATE_FORMATTER) : null;
            LocalTime startTime = request.getParameter("startTime") != null ?
                LocalTime.parse(request.getParameter("startTime"), DateTimeUtil.TIME_FORMATTER) : null;
            LocalTime endTime = request.getParameter("endTime") != null ?
                LocalTime.parse(request.getParameter("endTime"), DateTimeUtil.TIME_FORMATTER) : null;

            request.setAttribute("meals", mealRestController.getAllByDateTime(startDate, endDate,
                startTime, endTime));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
        else
        {
            Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("calories")), 1);

            log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
            mealRestController.create(meal);
            response.sendRedirect("meals");
        }
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
                request.setAttribute("meals", mealRestController.getAll());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
