package com.leeroy.forwordpanel.forwordpanel.job;

import com.leeroy.forwordpanel.forwordpanel.common.util.BeanCopyUtil;
import com.leeroy.forwordpanel.forwordpanel.dto.UserPortForwardDTO;
import com.leeroy.forwordpanel.forwordpanel.model.User;
import com.leeroy.forwordpanel.forwordpanel.model.UserPortForward;
import com.leeroy.forwordpanel.forwordpanel.service.UserPortForwardService;
import com.leeroy.forwordpanel.forwordpanel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 统计用户使用的流量
 */
//@EnableScheduling
//@Configurable
//@Component
public class DataUsageJob {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPortForwardService userPortForwardService;


    @Scheduled(cron = "0 0/5 * * * ?")
    public void execute() {
        List<User> expireUserList = userService.findEnableUserList();
        expireUserList.forEach(user -> {
            List<UserPortForward> userForwardList = userPortForwardService.findUserForwardList(user.getId());
            List<UserPortForwardDTO> userPortForwardDTOList = BeanCopyUtil.copyListProperties(userForwardList, UserPortForwardDTO::new);
            //查询用户设置的转发
            Map<UserPortForwardDTO, String> portFlowMap = userPortForwardService.getPortFlowMap(userPortForwardDTOList);
        });
    }
}
