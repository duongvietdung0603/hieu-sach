package com.thuvien.thuvien.service;

import com.thuvien.thuvien.model.User;
import com.thuvien.thuvien.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public User findByEmail(String s) {
        return userRepository.findByEmail(s);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Khong tim thay id: "+id));
    }

    public List<User> findAll() {
         return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
