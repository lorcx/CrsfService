package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: lx
 * @Date: Created in 2019/4/14
 */
//@Order(value = 2)
//@WebFilter(filterName = "ValidateSalt", urlPatterns = "/*")
public class ValidateSalt implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        String csrfToken = request.getHeader("CSRF_TOKEN");
        if (StringUtils.isBlank(csrfToken)) {
            throw new ServletException("没有权限");
        }

        String salt = (String) session.getAttribute(Constants.CSRF_SALT);
        if (!Objects.equals(csrfToken, salt)) {
            throw new ServletException("没有权限");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
