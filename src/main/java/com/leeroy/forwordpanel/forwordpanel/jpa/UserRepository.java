package com.leeroy.forwordpanel.forwordpanel.jpa;

import com.leeroy.forwordpanel.forwordpanel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
