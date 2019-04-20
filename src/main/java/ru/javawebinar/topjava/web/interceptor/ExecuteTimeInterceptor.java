package ru.javawebinar.topjava.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExecuteTimeInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(ExecuteTimeInterceptor.class);

    //before the actual handler will be executed
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {

        long startTime = System.currentTimeMillis();
        request.setAttribute("st", startTime);

        return true;
    }

    //after the handler is executed
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws Exception {

        long startTime = (Long) request.getAttribute("st");

        long endTime = System.currentTimeMillis();

        long executeTime = endTime - startTime;

//        //modified the exisitng modelAndView
//        modelAndView.addObject("executeTime", executeTime);
//
//        //log it
        if (logger.isDebugEnabled()) {
            logger.debug("[" + handler + "] executeTime : " + executeTime + "ms");
        }
    }
}
