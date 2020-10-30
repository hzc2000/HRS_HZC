package org.hzc.controller;

import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.hzc.service.SecurityService;
import org.hzc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController  extends BaseController {


    @Autowired
    SecurityService securityService;

    @Autowired
    UserService userService;
    @GetMapping
    public String show(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }


    @PostMapping("/handle")
    //@RequestMapping(method = RequestMethod.POST, value = "/login/handle")
    public String handle(User user, Errors errors, HttpSession session) throws HRSException {
        if(errors.hasErrors()){
               errors.getAllErrors().forEach(str->System.out.println(str));
            return "login";
        }

         user =  securityService.login(user.getPhone(), user.getPassword(), user.getRole());
        if(user == null)
        {

            return "my_error";
        }

        session.setAttribute("user",user);

        return "redirect:/index";
    }


    @PostMapping("/logout")
    public @ResponseBody String logout(SessionStatus status, HttpSession session) {
        session.invalidate();
        status.setComplete();
        return "logout";
        // 结束会话
        //
//
    }
}
