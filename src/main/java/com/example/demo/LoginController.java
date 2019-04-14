package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

/**
 * @Author: lx
 * @Date: Created in 2019/4/13
 */
@Controller
public class LoginController {

    public static final String LOGIN_SESSION = "LOGIN_SESSION";

    @GetMapping("/")
    @AllowAccess
    public String toLogin(Model model) {
        model.addAttribute("msg", "abcd");
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, String userName, String password, Model model) {
        session.setAttribute(LOGIN_SESSION, userName + password);
        model.addAttribute("msg", "login success");
        return "redirect:account";
    }

    @RequestMapping("/transfer")
    public String transfer(HttpSession session, String transferFrom, String transferTo, Integer amount, Model model) {
        Object attribute = session.getAttribute(LOGIN_SESSION);
        if (attribute == null) {
            System.out.println("无权限");
            model.addAttribute("msg", "请登录");
            return "main";
        }

        String msg = "由 " + transferFrom + "向" + transferTo + " 转账" + amount + "元";
        System.out.println(msg);
        model.addAttribute("msg", msg);
        return "main";
    }

    @RequestMapping("/ajax")
    public String ajaxTest() {
        return "ajax";
    }

    @RequestMapping("/ajaxPost")
    @ResponseBody
    public String ajaxPost(String name, Integer age) {
        System.out.println(name + "-" + age);
        return "name=" + name + " - age=" + age;
    }

    @RequestMapping("/account")
    @AllowAccess
    public String toAccount() {
        return "account";
    }
}
