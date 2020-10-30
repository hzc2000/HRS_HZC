package org.hzc.controller;

import org.hzc.editor.LocalDateEditor;
import org.hzc.entity.User;
import org.hzc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/register")
@SessionAttributes("majors")
public class RegisterController  extends BaseController {
    @Autowired
    private UserService userService;




    @GetMapping
    public String show(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/handle")
    public String handle(@Valid @ModelAttribute("user") User user, Errors errors, Model model, SessionStatus status) throws Exception {


        if(errors.hasErrors()) {
            return "register";
        }else {
            userService.create(user);
            // 结束临时会话
            model.addAttribute("user", user);
            status.setComplete();
            return "redirect:/login";
        }
    }



    @InitBinder
    public void initPropertyEditors(WebDataBinder binder){
        binder.registerCustomEditor(LocalDate.class, new LocalDateEditor());
    }
}
