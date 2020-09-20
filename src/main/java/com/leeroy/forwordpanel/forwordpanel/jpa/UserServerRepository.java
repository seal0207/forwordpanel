package com.leeroy.forwordpanel.forwordpanel.jpa;

import com.leeroy.forwordpanel.forwordpanel.model.User;
import com.leeroy.forwordpanel.forwordpanel.model.UserServer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserServerRepository extends JpaRepository<UserServer, Integer> {
}
