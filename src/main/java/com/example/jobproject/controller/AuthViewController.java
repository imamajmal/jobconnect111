package com.example.jobproject.controller;

import com.example.jobproject.entity.User;
import com.example.jobproject.repository.UserRepository;
import com.example.jobproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthViewController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/register";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/login?registered";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
public String dashboard(Authentication authentication, Model model) {
    String email = authentication.getName();  // Spring Security gives the email here
    User user = userRepository.findByEmail(email).orElse(null);

    if (user != null) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("role", user.getRole().name());

        if (user.getRole().name().equals("JOB_SEEKER")) {
            return "seeker-dashboard";
        } else if (user.getRole().name().equals("EMPLOYER")) {
            return "employer-dashboard";
        }
    }
    return "error";
}


}


