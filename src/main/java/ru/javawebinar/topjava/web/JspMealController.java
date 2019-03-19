package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
public class JspMealController {
    @Autowired
    private MealService service;

    @GetMapping("/meals")
    public String mealsList(Model model, HttpServletRequest request) {
        int userId = SecurityUtil.authUserId();
        model.addAttribute("meals", MealsUtil.getWithExcess(service.getAll(userId),
                SecurityUtil.authUserCaloriesPerDay()));
        return "meals";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id)
    {
        int userId = SecurityUtil.authUserId();
        service.delete(id, userId);
        return "redirect:/meals";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(Model model, @PathVariable int id)
    {
        int userId = SecurityUtil.authUserId();
        model.addAttribute("meal", service.get(id, userId));
        return "mealForm";
    }

    @PostMapping("/update/meals")
    public String postUpdate()
    {
        return "redirect:/meals";
    }

    @PostMapping("/create/meals")
    public String postCreate()
    {
        return "";
    }


}
