package com.thuvien.thuvien.repository;

 import com.thuvien.thuvien.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
