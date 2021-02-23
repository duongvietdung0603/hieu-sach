package com.thuvien.thuvien.config;

import com.thuvien.thuvien.model.Role;
import com.thuvien.thuvien.model.User;
import com.thuvien.thuvien.repository.RoleRepository;
import com.thuvien.thuvien.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.UUID;


@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        // Roles
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }

        if (roleRepository.findByName("ROLE_THUKHO") == null) {
            roleRepository.save(new Role("ROLE_THUKHO"));
        }

        if (roleRepository.findByName("ROLE_QUANLYTHE") == null) {
            roleRepository.save(new Role("ROLE_QUANLYTHE"));
        }
        if (roleRepository.findByName("ROLE_KETOAN") == null) {
            roleRepository.save(new Role("ROLE_KETOAN"));
        }

        // Admin account
        if (userRepository.findByEmail("admin@gmail.com") == null) {
            User user = new User();
            user.setName("Admin");
            user.setEmail("admin@gmail.com");
            user.setPhone("1234");
            user.setPassword(passwordEncoder.encode("123"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_ADMIN"));
            user.setRoles(roles);
            userRepository.save(user);
        }

        // Admin account
        if (userRepository.findByEmail("nhanvien1@gmail.com") == null) {
            User user = new User();
            user.setName("Thủ kho");
            user.setEmail("nhanvien1@gmail.com");
            user.setPhone("12345");
            user.setPassword(passwordEncoder.encode("123"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_THUKHO"));
            user.setRoles(roles);
            userRepository.save(user);
        }

        // Member account
        if (userRepository.findByEmail("nhanvien2@gmail.com") == null) {
            User user = new User();
            user.setName("Quản lý thẻ");
            user.setEmail("nhanvien2@gmail.com");
            user.setPhone("123456");
            user.setPassword(passwordEncoder.encode("123"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_QUANLYTHE"));
            user.setRoles(roles);
            userRepository.save(user);
        }

        // Member account
        if (userRepository.findByEmail("nhanvien3@gmail.com") == null) {
            User user = new User();
            user.setName("Kế toán");
            user.setEmail("nhanvien3@gmail.com");
            user.setPhone("1234567");
            user.setPassword(passwordEncoder.encode("123"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_KETOAN"));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }


}