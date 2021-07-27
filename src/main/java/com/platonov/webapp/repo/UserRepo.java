package com.platonov.webapp.repo;

import com.platonov.webapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String name);
}
