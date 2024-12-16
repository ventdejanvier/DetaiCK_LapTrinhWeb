package com.example.tuvan.controller.auth;

import com.example.tuvan.config.Constants;
import com.example.tuvan.entity.*;
import com.example.tuvan.repository.CustomerRepository;
import com.example.tuvan.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller

public class OtpController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmailSenderService emailSenderService;
    Constants constants = new Constants();


    @RequestMapping(value = "otp-check", method = RequestMethod.GET)
    public String indexOtp() {
        return "auth/otpConfirm";
    }

    @RequestMapping(value = "recover", method = RequestMethod.GET)
    public String recover() {
        return "auth/recoverPage";
    }

    @RequestMapping(value = "confirm-otp", method = RequestMethod.POST)
    public String checkOtp(HttpSession session, @RequestParam("otp") String otp, Model model) {
        String otpRegister = (String) session.getAttribute("otp-register");
        if (otp.equals(otpRegister)) {
            User User = new User();
            User.setEmail((String) session.getAttribute("email"));
            User.setPassword(passwordEncoder.encode((String) session.getAttribute("password")));
            String phone = (String) session.getAttribute("phone");
            String fullName = (String) session.getAttribute("fullName");
            String address = (String) session.getAttribute("address");
            User.setPhoneNumber(phone);
            User.setStatus(1);

            Role client = roleService.findById(3L).get();

            Set<Role> roleEntities = new HashSet<>();

            roleEntities.add(client);
            Customer Customer = new Customer();
            Customer.setFullName(fullName);
            Customer.setStatus(1);
            Customer.setAddress(address);

            User.setRoleEntities(roleEntities);
            User.setCreatedBy("SUPER_ADMIN");
            User.setUpdatedBy("SUPER_ADMIN");
            Customer.setCreatedBy("SUPER_ADMIN");
            Customer.setUpdatedBy("SUPER_ADMIN");
            User user = userService.saveUser(User);
            Customer.setUser(user);

            Customer = customerRepository.save(Customer);
            user.setCustomer(Customer);
            userService.saveUser(user);
            return "redirect:/";
        }
        model.addAttribute("mess", "OTP is not correct! Please check your email.");
        return "auth/otpConfirm";
    }

    @RequestMapping(value = "send-otp-recover", method = RequestMethod.POST)
    public String getMail(@RequestParam("emailaddress") String email, HttpSession session) {
        session.setAttribute("emailToReset", email);
        String otpCode = constants.otpCode();
        String subject = "Hello Here Is Your Code OTP!";
        String mess = "Hi You@" + " " + "Here is your OTP Code: " + otpCode + " Please input to form!" + "\n Thanks!";
        this.emailSenderService.sendEmail(email, subject, mess);
        session.setAttribute("recoverOtp", otpCode);
        session.setMaxInactiveInterval(360);
        return "auth/confirmOtpRecover";
    }

    @RequestMapping(value = "confirm-otp-recover", method = RequestMethod.POST)
    public String recover(@RequestParam("otp") String otp, Model model, HttpSession session) {
        if (session.getAttribute("recoverOtp").equals(otp)) {
            return "auth/confirmNewPassword";
        }
        model.addAttribute("mess", "OTP is not correct! Please check your email.");
        return "auth/confirmOtpRecover";
    }

    @RequestMapping(value = "save-new-password", method = RequestMethod.POST)
    public String saveNewPassword(@RequestParam("password") String password, HttpSession session) {
        String email = (String) session.getAttribute("emailToReset");
        if (userService.recoverPassword(passwordEncoder.encode(password), email) == 1) {
            return "redirect:/login";
        }
        return "auth/confirmNewPassword";
    }
}