package com.leeroy.forwordpanel.forwordpanel.jpa;

import com.leeroy.forwordpanel.forwordpanel.model.ForwardFlow;
import com.leeroy.forwordpanel.forwordpanel.model.UserPort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForwardFlowRepository extends JpaRepository<ForwardFlow, Integer> {
}
