package com.rk.springbootmysql.repository;
import com.rk.springbootmysql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Additional custom queries if needed
}
