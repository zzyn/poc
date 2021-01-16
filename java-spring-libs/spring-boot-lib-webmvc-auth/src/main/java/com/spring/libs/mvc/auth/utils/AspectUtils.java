package com.spring.libs.mvc.auth.utils;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class AspectUtils {

    public static boolean isOptionsRequest(JoinPoint joinPoint) {

        Class clazz = joinPoint.getTarget().getClass();

        if (clazz.isAnnotationPresent(RestController.class) || clazz.isAnnotationPresent(Controller.class)) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {
                return true;
            }
        }

        return false;
    }
}
