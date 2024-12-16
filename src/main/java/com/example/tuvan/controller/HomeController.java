package com.example.tuvan.controller;

import com.example.tuvan.entity.Question;
import com.example.tuvan.repository.DepartmentRepository;
import com.example.tuvan.repository.QuestionRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    @GetMapping("")
    private String indexHome(Model model) {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("departments", departmentRepository.findAll());
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);


        if (authorities.stream().anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()))) {
            model.addAttribute("email", SecurityContextHolder.getContext().getAuthentication().getName());
            return "redirect:/admin/home";
        } else if (authorities.stream().anyMatch(authority -> "ROLE_CONSULTANT".equals(authority.getAuthority()))) {
            model.addAttribute("email", SecurityContextHolder.getContext().getAuthentication().getName());
            return "redirect:/consultant/home";
        } else if (authorities.stream().anyMatch(authority -> "ROLE_CLIENT".equals(authority.getAuthority()))) {
            model.addAttribute("email", SecurityContextHolder.getContext().getAuthentication().getName());
            return "redirect:/client/home";
        } else {
            return "guest/index";
        }
    }

    @GetMapping("/home")
    private String homePage(Model model) {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("departments", departmentRepository.findAll());
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);


        if (authorities.stream().anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()))) {
            model.addAttribute("email", SecurityContextHolder.getContext().getAuthentication().getName());
            return "redirect:/admin/home";
        } else if (authorities.stream().anyMatch(authority -> "ROLE_CONSULTANT".equals(authority.getAuthority()))) {
            model.addAttribute("email", SecurityContextHolder.getContext().getAuthentication().getName());
            return "redirect:/consultant/home";
        } else if (authorities.stream().anyMatch(authority -> "ROLE_CLIENT".equals(authority.getAuthority()))) {
            model.addAttribute("email", SecurityContextHolder.getContext().getAuthentication().getName());
            return "redirect:/client/home";
        } else {
            return "guest/index";
        }
    }

    @GetMapping("/index")
    private String index(Model model) {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("departments", departmentRepository.findAll());
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);


        if (authorities.stream().anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()))) {
            model.addAttribute("email", SecurityContextHolder.getContext().getAuthentication().getName());
            return "redirect:/admin/home";
        } else if (authorities.stream().anyMatch(authority -> "ROLE_CONSULTANT".equals(authority.getAuthority()))) {
            model.addAttribute("email", SecurityContextHolder.getContext().getAuthentication().getName());
            return "redirect:/consultant/home";
        } else if (authorities.stream().anyMatch(authority -> "ROLE_CLIENT".equals(authority.getAuthority()))) {
            model.addAttribute("email", SecurityContextHolder.getContext().getAuthentication().getName());
            return "redirect:/client/home";
        } else {
            return "guest/index";
        }
    }

    @GetMapping("/403")
    public String error() {
        return "403";
    }


}
