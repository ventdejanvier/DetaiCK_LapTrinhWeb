package com.example.tuvan.controller.client;


import com.example.tuvan.entity.Customer;
import com.example.tuvan.entity.Department;
import com.example.tuvan.entity.Question;
import com.example.tuvan.payload.dto.QuestionDTO;
import com.example.tuvan.repository.CustomerRepository;
import com.example.tuvan.repository.DepartmentRepository;
import com.example.tuvan.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/client")
public class ClientHomeController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/home")
    public String home(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("email", email);
        model.addAttribute("departments", departmentRepository.findAll());
        return "client/index";
    }

    @GetMapping("/questions/{departmentId}")
    @ResponseBody
    public ResponseEntity<List<QuestionDTO>> getQuestionsByDepartment(@PathVariable Long departmentId) {
        List<Question> questions = questionRepository.findByDepartmentId(departmentId);

        List<QuestionDTO> questionDTOs = questions.stream()
                .map(question -> {
                    QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);
                    questionDTO.setConsultantEmail(question.getConsultant().getUser().getEmail());
                    questionDTO.setConsultantName(question.getConsultant().getFullName());
                    return questionDTO;
                })
                .collect(Collectors.toList());

        if (questionDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(questionDTOs);  // Return 204 No Content if no questions
        }

        return ResponseEntity.ok(questionDTOs);  // Return 200 OK with the questions as JSON
    }
    @PostMapping("/save-question")
    @ResponseBody
    public Response saveQuestion(@RequestBody QuestionDTO questionDTO) {
        try {
            Department department = departmentRepository.findById(questionDTO.getDepartmentId()).orElseThrow();

            Question question = new Question();
            question.setTitle(questionDTO.getTitle());
            question.setContent(questionDTO.getContent());
            question.setDepartment(department);

            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Customer customer = customerRepository.findByUserEmail(email);
            question.setCustomer(customer);

            // Save question
            questionRepository.save(question);

            return new Response(true, "Câu hỏi đã được gửi thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, "Có lỗi xảy ra. Vui lòng thử lại.");
        }
    }

    // Response class to send success/failure message
    public static class Response {
        private boolean success;
        private String message;

        public Response(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}