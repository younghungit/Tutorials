package com.younghun.library.controller;

import com.younghun.library.config.ErrorCode;
import com.younghun.library.config.UserForm;
import com.younghun.library.model.User;
import com.younghun.library.repository.UserRepository;
import com.younghun.library.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String authenticate(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.findUserByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("user", user);
            return "redirect:/home";
        }
        model.addAttribute("error", "Invalid credentials");
        return "home";
    }
    @GetMapping("/signup")
    public String signup(UserForm userForm) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserForm userForm, BindingResult bindingResult) {
        System.out.println(userForm);
        if(bindingResult.hasErrors()) {
            return "signup";
        }
        if(!userForm.getPassword1().equals(userForm.getPassword2())) {
            bindingResult.rejectValue("password1",
                    ErrorCode.INVALID_PASSWORD.getCode(),ErrorCode.INVALID_PASSWORD.getMessage());
            return "signup";

        }
        //username duplication
        if(userService.createUser(userForm)==null){
            bindingResult.rejectValue("username",
                    ErrorCode.ALREADY_EXIST_USERNAME.getCode(),ErrorCode.ALREADY_EXIST_USERNAME.getMessage());
            return "signup";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
