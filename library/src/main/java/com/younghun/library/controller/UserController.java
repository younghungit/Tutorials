package com.younghun.library.controller;

import com.younghun.library.model.User;
import com.younghun.library.repository.UserRepository;
import com.younghun.library.service.UserService;
import jakarta.servlet.http.HttpSession;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String authenticate(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session) {
        User user = userService.findUserByUsername(username);

        if (user == null) {
            System.out.println("User not found");
            return "redirect:/login?error";
        }

        System.out.println("Stored Password: " + user.getPassword());
        System.out.println("Entered Password: " + password);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("Password does not match");
            return "redirect:/login?error";
        }

        session.setAttribute("user", user);


        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password) {
        userService.registerUser(username, password);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    @GetMapping("/")
    public String showHomePage(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        return "home";
    }
}
