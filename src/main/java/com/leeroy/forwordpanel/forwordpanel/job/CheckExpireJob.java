package com.leeroy.forwordpanel.forwordpanel.job;

import com.leeroy.forwordpanel.forwordpanel.model.User;
import com.leeroy.forwordpanel.forwordpanel.service.ForwardFlowService;
import com.leeroy.forwordpanel.forwordpanel.service.ForwardService;
import com.leeroy.forwordpanel.forwordpanel.service.UserPortService;
import com.leeroy.forwordpanel.forwordpanel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 检查用户账户到期的任务
 */
@EnableScheduling
@Configurable
@Component
public class CheckExpireJob {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPortService userPortService;

    @Autowired
    private ForwardFlowService forwardFlowService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void execute() {
        List<User> expireUserList = userService.findExpireUserList();
        for (User user : expireUserList) {
            if (user.getExpireTime() == null||user.getUserType()<=0) {
                continue;
            }
            userPortService.disableUserPort(user.getId());
            userService.disableUserById(user.getId());
        }
        //流量用超的用户
        List<User> enableUserList = userService.findEnableUserList();
        for (User user : enableUserList) {
            if ( user.getDataLimit() == null || user.getDataLimit() <= 0L || user.getUserType()<=0) {
                continue;
            }
            Long total = forwardFlowService.getUserFlowTotal(user.getId());
            if (total >= user.getDataLimit() * 1024 * 1024 * 1024) {
                userPortService.disableUserPort(user.getId());
                userService.disableUserById(user.getId());
            }
        }
    }
}
