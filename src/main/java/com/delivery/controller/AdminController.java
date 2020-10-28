package com.delivery.controller;

import com.delivery.entity.Admin;
import com.delivery.entity.Staff;
import com.delivery.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;
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
            Admin login = adminService.login(admin);
            System.out.println("login = " + login);
            if (null!=login) {
                session.setAttribute("admin", login);
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
     * 管理员修改密码
     * @param password
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/admin_editPassword")
    public void admin_editPassword(String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        admin.setPassword(password);
        boolean flog= adminService.editPassword(admin);
        response.getWriter().write("1");
    }

    /**
     * 退出登录
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/exitLogin")
    public String exitLogin(Model model,HttpServletRequest request) {
        request.getSession().invalidate();
        model.addAttribute("msg2","您已退出登录!" );
        return "login";
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
