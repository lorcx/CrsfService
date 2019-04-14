package com.example.demo;

import java.lang.annotation.*;

/**
 * @Author: lx
 * @Date: Created in 2019/4/14
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AllowAccess {
}
