package com.darkcom.backend.repository;

import com.darkcom.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByAccount(String account);
}
