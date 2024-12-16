package com.example.tuvan.controller.consultant;


import com.example.tuvan.payload.dto.UserDto;
import com.example.tuvan.repository.ConsultantRepository;
import com.example.tuvan.repository.CustomerRepository;
import com.example.tuvan.repository.QuestionRepository;
import com.example.tuvan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/consultant")
public class ConsultantHomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ConsultantRepository consultantRepository;

    @GetMapping("/home")
    public String home(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("email", email);
        model.addAttribute("countCustomers", customerRepository.count());
        model.addAttribute("ques", questionRepository.count());
        model.addAttribute("countConsult", consultantRepository.count());
        return "consultant/dashboard";
    }

    @GetMapping("/profile")
    public String adminProfile(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("email", email);

        UserDto userDto = userService.getProfile(email);
        model.addAttribute("user", userDto);
        return "consultant/profile";
    }

    @PostMapping("/updateProfile")
    public String updateProfileAdmin(@ModelAttribute(name = "user") UserDto userDto, @RequestParam("file") MultipartFile file) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.updateProfile(userDto, file, email);
        return "redirect:/consultant/profile";
    }

}