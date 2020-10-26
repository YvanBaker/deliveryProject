package com.delivery.controller;

import com.delivery.entity.Admin;
import com.delivery.entity.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    /**
     * 到登录页面
     *
     * @return
     */
    @RequestMapping("/loginView")
    public String loginView() {
        return "login";
    }

    /**
     * 管理员登录
     * @param admin
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(Admin admin, String checkcode, Model model, HttpSession session) {
        String key = (String) session.getAttribute("key");
        if (key.equals(checkcode)) {
            //通过验证
            if (admin.getUserName().equals("aaa") && admin.getPassword().equals("sss")) {
                session.setAttribute("admin", admin);
                return "manage";
            } else {
                model.addAttribute("msg", "用户名或密码不正确！");
                return "login";
            }
        } else {
            model.addAttribute("msg", "验证码不正确！");
            return "login";
        }
    }

    /**
     *
     *
     * @return
     */
    @RequestMapping("/registerView")
    public String registerView() {
        return "register";
    }

    /**
     * 注册
     *
     * @param admin
     * @param session
     * @return
     */
    @RequestMapping("/register")
    public String register(Admin admin, HttpSession session) {
        return "register";
    }


    @RequestMapping("/test")
    public String test(ModelAndView modelAndView, HttpServletResponse response) throws IOException {
        return "register";
    }

}
