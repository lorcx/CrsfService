package com.example.demo;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;

/**
 * @Author: lx
 * @Date: Created in 2019/4/14
 */
public class LoadSaltInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("....................loadSalt");
        HttpSession session = request.getSession();
        String salt = (String) session.getAttribute(Constants.CSRF_SALT);
        //if (StringUtils.isBlank(salt)) {
            salt = RandomStringUtils.random(32, 0, 0, true, true, null, new SecureRandom());
            session.setAttribute(Constants.CSRF_SALT, salt);
        //}

        return true;
    }
}
