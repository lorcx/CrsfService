package com.example.demo;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * @Author: lx
 * @Date: Created in 2019/4/14
 */
//@Order(value = 1)
//@WebFilter(filterName = "LoadSalt", urlPatterns = "/*")
public class LoadSalt implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        /*HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        String salt = (String) session.getAttribute(Constants.CSRF_SALT);
        if (StringUtils.isBlank(salt)) {
            salt = RandomStringUtils.random(32, 0, 0, true, true, null, new SecureRandom());
            session.setAttribute(Constants.CSRF_SALT, salt);
        }*/

        filterChain.doFilter(servletRequest, response);
    }
}
