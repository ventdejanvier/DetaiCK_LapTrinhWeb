package com.example.tuvan.service;


import com.example.tuvan.entity.Customer;
import com.example.tuvan.entity.User;
import com.example.tuvan.payload.dto.CustomerDto;
import com.example.tuvan.repository.CustomerRepository;
import com.example.tuvan.repository.UserRepository;
import com.example.tuvan.utils.ImageUtils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    
    private StorageService storageService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    
    private ModelMapper modelMapper;
    @Autowired
    
    private PasswordEncoder passwordEncoder;


    
    public Customer saveEntity(Customer Customer) {
        return customerRepository.save(Customer);
    }

    
    public Long countCustomers() {
        return customerRepository.count();
    }

    
    public List<CustomerDto> getAll() {
        List<Customer> customerEntities = customerRepository.getAll();
        return customerEntities.stream().map(Customer -> {
            CustomerDto customerDto = modelMapper.map(Customer, CustomerDto.class);
            if(Customer.getUser() == null) {
                customerDto.setEmail("");
            }
            else {
                customerDto.setEmail(Customer.getUser().getEmail());
            }
            if(Customer.getUser().getAvatar() != null) {
                customerDto.setAvatar(Customer.getUser().getAvatar());
            }
            else {
                customerDto.setAvatar("");
            }
            return customerDto;
        }).collect(Collectors.toList());
    }

    
    public CustomerDto getProfile(String email) {
        User userEntity = userRepository.findByEmailAndStatus(email, 1).get();
        Customer Customer = userEntity.getCustomer();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(Customer.getId());
        customerDto.setFullName(Customer.getFullName());
        customerDto.setAddress(Customer.getAddress());
        if(Customer.getDob() != null) {
            customerDto.setDob(Customer.getDob().toString());
        } else customerDto.setDob("");


        customerDto.setAddress(Customer.getAddress());
        customerDto.setEmail(email);
        customerDto.setPhoneNumber(userEntity.getPhoneNumber());
        String imageEntity = userEntity.getAvatar();

        if(imageEntity != null) {
            customerDto.setAvatar(imageEntity);
        }
        else {
            customerDto.setAvatar("");
        }
        customerDto.setPassword("");
        customerDto.setNewPassword("");
        return customerDto;
    }

    
    @Transactional
    public void updateProfile(CustomerDto customerDto, MultipartFile file, String email) {
        User userEntity = userRepository.findByEmailAndStatus(email, 1).get();
        if (!file.isEmpty() && ImageUtils.isImageFile(file)) {
            if(userEntity.getAvatar() == null) {
                String fileName = "user_" + userEntity.getId();
                String saveLink = "";
                saveLink = storageService.uploadFile(file, fileName);
                userEntity.setAvatar(saveLink);
            }
        }
        Customer customer = userEntity.getCustomer();
        Customer saveCustomer = new Customer();
        saveCustomer.setId(customerDto.getId());
        saveCustomer.setCreatedAt(customer.getCreatedAt());
        saveCustomer.setCreatedBy(customer.getCreatedBy());
        saveCustomer.setStatus(1);
        saveCustomer.setId(customerDto.getId());
        saveCustomer.setFullName(customerDto.getFullName());
        saveCustomer.setAddress(customerDto.getAddress());
        String DATE_FORMAT="yyyy-MM-dd";
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate date = LocalDate.parse(customerDto.getDob(), formatter);
        saveCustomer.setDob(date);
        saveCustomer.setUpdatedBy(email);
        saveCustomer.setUpdatedAt(LocalDateTime.now());
        saveCustomer.setCreatedAt(userEntity.getCreatedAt());
        saveCustomer.setCreatedBy(userEntity.getCreatedBy());

        customerRepository.save(saveCustomer);

        userEntity.setEmail(customerDto.getEmail());
        if(Objects.equals(customerDto.getNewPassword(), "")) {
            userEntity.setPassword(userEntity.getPassword());
        }
        else {
            userEntity.setPassword(passwordEncoder.encode(customerDto.getNewPassword()));
        }
        userEntity.setPhoneNumber(customerDto.getPhoneNumber());
        userEntity = userRepository.save(userEntity);
        saveCustomer.setUser(userEntity);
        saveCustomer = customerRepository.save(saveCustomer);
        userEntity.setCustomer(saveCustomer);
        userRepository.save(userEntity);
    }

    
    public Long getIdByEmail(String email) {
        return customerRepository.findByUserEmail(email).getId();
    }

    
    public Customer findByEmail(String email) {
        return customerRepository.findByUserEmail(email);
    }

}
