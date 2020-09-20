package com.leeroy.forwordpanel.forwordpanel.jpa;

import com.leeroy.forwordpanel.forwordpanel.model.Port;
import com.leeroy.forwordpanel.forwordpanel.model.UserPort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPortRepository extends JpaRepository<UserPort, Integer> {
}
