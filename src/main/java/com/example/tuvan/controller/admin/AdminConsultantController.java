package com.example.tuvan.controller.admin;

import com.example.tuvan.entity.Consultant;
import com.example.tuvan.entity.Department;
import com.example.tuvan.entity.Role;
import com.example.tuvan.entity.User;
import com.example.tuvan.payload.dto.ConsultantDto;
import com.example.tuvan.repository.ConsultantRepository;
import com.example.tuvan.repository.DepartmentRepository;
import com.example.tuvan.repository.RoleRepository;
import com.example.tuvan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin/consultant")
public class AdminConsultantController {
    
    @Autowired
    private ConsultantRepository consultantRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    @GetMapping("")
    public String consultant(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("email", email);
        model.addAttribute("consultants", consultantRepository.findAll());
        return "admin/consultant/list";
    }
    @GetMapping("/insertPage")
    public String insertProductPage(Model model) {
        ConsultantDto consultantDto = new ConsultantDto();
        model.addAttribute("consultant", consultantDto);
        model.addAttribute("departments", departmentRepository.findAll());
        return "admin/consultant/insert";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute(name = "consultant") ConsultantDto consultantDto) {
        System.out.println(consultantDto);

        Consultant consultant = new Consultant();
        consultant.setFullName(consultantDto.getFullName());

        consultant.setDob(LocalDate.parse(consultantDto.getDob()));
        consultant.setAddress(consultantDto.getAddress());
        Department department = departmentRepository.findById(consultantDto.getDepartmentId()).get();
        consultant.setDepartment(department);

        User user = new User();
        user.setEmail(consultantDto.getEmail());
        user.setStatus(1);
        user.setPhoneNumber(consultantDto.getPhoneNumber());

        user.setPassword(passwordEncoder.encode("123"));
        Role role = roleRepository.findById(2L).get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoleEntities(roles);

        user = userRepository.save(user);


        consultant.setUser(user);
        consultantRepository.save(consultant);

        return "redirect:/admin/consultant";
    }


    @GetMapping("/updatePage/{id}")
    public String getFormUpdateconsultant(@PathVariable("id") Long id, Model model) {
        Consultant consultant = consultantRepository.findById(id).orElse(null);

        if (consultant != null) {
            ConsultantDto consultantDto = new ConsultantDto();
            consultantDto.setId(consultant.getId());
            consultantDto.setFullName(consultant.getFullName());
            consultantDto.setEmail(consultant.getUser().getEmail());
            consultantDto.setDob(consultant.getDob().toString());
            consultantDto.setAddress(consultant.getAddress());
            consultantDto.setPhoneNumber(consultant.getUser().getPhoneNumber());


            if (consultant.getDepartment() != null) {
                consultantDto.setDepartmentId(consultant.getDepartment().getId());
                consultantDto.setDepartmentName(consultant.getDepartment().getName());
            }

            model.addAttribute("consultant", consultantDto);
        }

        // Lấy tất cả các phòng ban và thêm vào model
        model.addAttribute("departments", departmentRepository.findAll());

        return "admin/consultant/update";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute(name = "consultant") ConsultantDto consultantDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Lấy Consultant từ cơ sở dữ liệu theo ID
        Consultant consultant = consultantRepository.findById(consultantDto.getId()).orElse(null);

        if (consultant != null) {
            // Cập nhật các trường của Consultant
            consultant.setFullName(consultantDto.getFullName());
            consultant.setDob(LocalDate.parse(consultantDto.getDob()));
            consultant.setAddress(consultantDto.getAddress());
            Department department = departmentRepository.findById(consultantDto.getDepartmentId()).get();
            consultant.setDepartment(department);
            User user = consultant.getUser();
            user.setEmail(consultantDto.getEmail());
            user.setPhoneNumber(consultantDto.getPhoneNumber());
            user.setStatus(1);


            consultantRepository.save(consultant);
        }

        return "redirect:/admin/consultant";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        consultantRepository.deleteById(id);
        return "redirect:/admin/consultant";
    }
}