package com.example.tuvan.service;

import com.example.tuvan.config.SecurityUser;
import com.example.tuvan.entity.*;
import com.example.tuvan.payload.dto.UserDto;
import com.example.tuvan.repository.RoleRepository;
import com.example.tuvan.repository.UserRepository;

import com.example.tuvan.utils.ImageUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
  
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StorageService storageService;

    public UserDto getProfile(String email) {
        User user = userRepository.findByEmailAndStatus(email, 1).get();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setNewPassword("");
        userDto.setPassword("");
        return userDto;
    }

    public void updateProfile(UserDto userDto, MultipartFile file, String email) {
        try{
            User userEntity = userRepository.findByEmailAndStatus(email, 1).orElseThrow();

            if (!file.isEmpty() && ImageUtils.isImageFile(file)) {
                if (userEntity.getAvatar() == null) {
                    String fileName = "user_" + userEntity.getId();
                    String saveLink = "";
                    saveLink = storageService.uploadFile(file, fileName);
                    userEntity.setAvatar(saveLink);
                }
            }

            userEntity.setUpdatedBy(email);
            userEntity.setUpdatedAt(LocalDateTime.now());

            userEntity.setEmail(userDto.getEmail());
            if (!userDto.getNewPassword().isEmpty()) {
                userEntity.setPassword(passwordEncoder.encode(userDto.getNewPassword()));
            } else {
                userEntity.setPassword(userEntity.getPassword());
            }
            userRepository.save(userEntity);
        }catch(Exception e) {
            System.out.println(e.getMessage());

        }
    }
    
    public Long findIdByEmail(String email) {
        return userRepository.findIdByEmail(email);
    }

    

    
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    

    
    public int recoverPassword(String password, String email) {
        return userRepository.updatePassword(password, email);
    }

    


    
    public boolean validateCredentials(String username, String password) {
        return userRepository.existsByEmailAndPassword(username, passwordEncoder.encode(password));
    }

    

    
    public void updateUserStatus(Long id, Integer status) {
        User user = userRepository.findById(id).get();
        user.setStatus(status);
        userRepository.save(user);
    }

    
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmailAndStatus(email, 1);
    }

    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByUsername = userRepository.findByEmailAndStatus(username, 1);
        if (userByUsername.isEmpty()) {
            System.out.println("Could not find user with that email: {}");
            throw new UsernameNotFoundException("Invalid credentials!");
        }
        User user = userByUsername.get();
        System.out.println(user);
        if (!user.getEmail().equals(username)) {
            System.out.println("Could not find user with that username: {}");
            throw new UsernameNotFoundException("Invalid credentials!");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoleEntities()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().name()));
        }
        System.out.println(grantedAuthorities);
        return new SecurityUser(user.getEmail(), user.getPassword(), true, true, true, true, grantedAuthorities,
                user.getEmail());
    }
}
