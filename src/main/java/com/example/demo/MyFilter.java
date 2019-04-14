package com.example.demo;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * @Author: lx
 * @Date: Created in 2019/4/14
 */
@Order(value = 1)
@WebFilter(filterName = "myFilter", urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String, String[]> parameterMap = servletRequest.getParameterMap();
        parameterMap.forEach((k, v) -> System.out.println(k + "-" + Arrays.toString(v)));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
