package com.thuvien.thuvien.controller;

import com.thuvien.thuvien.model.Role;
import com.thuvien.thuvien.model.Sach;
import com.thuvien.thuvien.model.User;
import com.thuvien.thuvien.repository.RoleRepository;
import com.thuvien.thuvien.service.SachService;
import com.thuvien.thuvien.service.TheLoaiSachService;
import com.thuvien.thuvien.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("")
    public String index(Model model){
        model.addAttribute("DanhSach", userService.findAll());
        return "User/danh_sach";
    }

    @GetMapping("/them")
    public String add(Model model){
        model.addAttribute("User", new User());
        model.addAttribute("Role", roleRepository.findAll());
        model.addAttribute("Active","Thêm mới");

        return "User/add";
    }

    @PostMapping("/save")
    public String add_post(@ModelAttribute User user, @RequestParam Long role ){
           Role role1 = roleRepository.findById(role).orElseThrow(()->new IllegalArgumentException("k có id này"));
           HashSet<Role> roles = new HashSet<>();
           roles.add(roleRepository.findByName(role1.getName()));
           user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
           userService.save(user);
        return "redirect:/user";
    }

    @GetMapping("/sua/{id}")
    public String sua(Model model,@PathVariable Long id){
        model.addAttribute("User", userService.findById(id));
        model.addAttribute("Role", roleRepository.findAll());
        model.addAttribute("Active","Sửa id "+id);

        return "User/add";
    }

    @GetMapping("/xoa/{id}")
    public String xoa(Model model,@PathVariable Long id){
        userService.deleteById(id);
        return "redirect:/user";
    }
}
