package com.example.tuvan.controller.auth;

import com.example.tuvan.payload.dto.UserDto;
import com.example.tuvan.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private  EmailSenderService emailSenderService;
    @Autowired
    private  PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String showLogin() {
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpSession session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.invalidate();
        return "redirect:/index";
    }

    @RequestMapping(value = "register")
    public String addUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "auth/register";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("user") UserDto user, @RequestParam(name = "role", defaultValue = "Student") String role, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "auth/register";
        }
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("mess", "Email đã tồn tại. Hãy nhập Email mới!");
            return "auth/register";
        }
        session.setAttribute("otp-register", otpCode());
        session.setMaxInactiveInterval(360);
        String subject = "Hello Here Is Your Code OTP!";
        String mess = "Hi You@" + " \nDear " + user.getEmail() + "Here is your OTP Code: " + session.getAttribute("otp-register") + " Please input to form!" + "\n Thanks!";
        this.emailSenderService.sendEmail(user.getEmail(), subject, mess);
        session.setAttribute("email", user.getEmail());
        session.setAttribute("fullName", user.getFullName());
        session.setAttribute("role", role);
        session.setAttribute("dob", user.getDob());
        session.setAttribute("phone", user.getPhone());
        session.setAttribute("gender", user.getGender());
        session.setAttribute("address", user.getAddress());
        session.setAttribute("password", user.getPassword());
        return "redirect:/otp-check";

    }

    @RequestMapping(value = "re-send")
    public String resend(HttpSession session) {
        session.removeAttribute("otp-register");
        session.setAttribute("otp-register", otpCode());
        session.setMaxInactiveInterval(360);
        String subject = "Hello Here Is Your Code OTP!";
        String mess = "Hi You@" + " \nDear " + session.getAttribute("email") + ", Here is your OTP Code: " + session.getAttribute("otp-register") + " Please input to form!" + "\n Thanks!";
        this.emailSenderService.sendEmail((String) session.getAttribute("email"), subject, mess);
        return "redirect:/otp-check";
    }

    public String otpCode() {
        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
        return String.valueOf(code);
    }


}
