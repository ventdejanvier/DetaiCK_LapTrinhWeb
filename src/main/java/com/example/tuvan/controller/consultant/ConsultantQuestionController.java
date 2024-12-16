package com.example.tuvan.controller.consultant;

import com.example.tuvan.entity.Consultant;
import com.example.tuvan.entity.Question;
import com.example.tuvan.repository.ConsultantRepository;
import com.example.tuvan.repository.DepartmentRepository;
import com.example.tuvan.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultant/question")
public class ConsultantQuestionController {

    @Autowired
    private ConsultantRepository consultantRepository;

    @Autowired
    private QuestionRepository questionRepository;


    @Autowired
    private DepartmentRepository departmentRepository;


    @GetMapping("")
    public String consultant(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("email", email);
        Consultant consultant = consultantRepository.findByUserEmail(email);
        model.addAttribute("questions", questionRepository.findByDepartmentId(consultant.getDepartment().getId()));
        return "consultant/question/list";
    }


    @GetMapping("/updatePage/{id}")
    public String getFormUpdateconsultant(@PathVariable("id") Long id, Model model) {
        Question question = questionRepository.findById(id).orElse(null);
        model.addAttribute("question", question);
        return "consultant/question/update";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute(name = "question") Question question, @RequestParam String hiddenReply) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Consultant consultant = consultantRepository.findByUserEmail(email);
        question.setReply(hiddenReply);
        question.setConsultant(consultant);
        questionRepository.save(question);
        return "redirect:/consultant/question";
    }
}