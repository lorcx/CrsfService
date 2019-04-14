package com.example.demo;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * @Author: lx
 * @Date: Created in 2019/4/13
 */
public class CsrfInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("....................csrfSalt");
        HttpSession session = request.getSession();

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AllowAccess annotation = handlerMethod.getMethod().getAnnotation(AllowAccess.class);
        if (annotation != null) {
            System.out.println("不校验");
            return true;
        }

        String csrfToken = request.getParameter(Constants.CSRF_TOKEN);
        if (StringUtils.isBlank(csrfToken)) {
            System.out.println("没有权限");
            handleError(response, "{code: 403, msg: '没有权限'}");
            return false;
        }

        String salt = (String) session.getAttribute(Constants.CSRF_SALT);
        if (!Objects.equals(csrfToken, salt)) {
            System.out.println("没有权限");
            handleError(response, "{code: 403, msg: '没有权限'}");
            return false;
        }
       /* salt = RandomStringUtils.random(32, 0, 0, true, true, null, new SecureRandom());
        session.setAttribute(Constants.CSRF_SALT, salt);*/
        //session.removeAttribute(Constants.CSRF_SALT);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void handleError(HttpServletResponse response, String message) throws IOException {
        response.reset();
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(message);
        writer.flush();
        writer.close();
    }
}

