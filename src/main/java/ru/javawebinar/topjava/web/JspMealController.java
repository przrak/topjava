package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

@Controller
public class JspMealController {
    @Autowired
    private MealService service;

    @GetMapping("/meals")
    public String users(Model model) {
        int userId = SecurityUtil.authUserId();
        model.addAttribute("meals", MealsUtil.getWithExcess(service.getAll(userId),
                SecurityUtil.authUserCaloriesPerDay()));
        return "meals";
    }
}
