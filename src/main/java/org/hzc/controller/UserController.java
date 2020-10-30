package org.hzc.controller;

import org.hzc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping
@Controller
public class UserController   extends BaseController {

    @GetMapping("/index")
    public String show(Model model) {
        return "index";
    }


    @GetMapping("/administrator")
    public String administrator_operation(HttpSession session) {
        User administrator = (User) session.getAttribute("user");
        if(administrator.getRole().name().equals("ADMINISTRATOR"))
        {
            return "administrator_operation";
        }else
        {
            return  "index";
        }
    }
}