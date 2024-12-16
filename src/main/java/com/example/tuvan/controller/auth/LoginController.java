package com.example.tuvan.controller.auth;

import com.example.tuvan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
public class LoginController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        boolean isValidCredentials = userService.validateCredentials(username, password);
        if (isValidCredentials) {
            return "redirect:/index";
        } else {
            model.addAttribute("error", "Sai email hoặc mật khẩu!");
            return "auth/login";
        }
    }
    @GetMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("error", "Sai email hoặc mật khẩu!");
        return "auth/loginError";
    }

    @GetMapping("/loginInactive")
    public String loginInactive() {
        return "auth/loginInactive";
    }
}

