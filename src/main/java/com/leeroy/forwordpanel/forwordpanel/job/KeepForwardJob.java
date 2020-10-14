package com.leeroy.forwordpanel.forwordpanel.job;

import com.leeroy.forwordpanel.forwordpanel.common.enums.ServerStatusEnum;
import com.leeroy.forwordpanel.forwordpanel.model.Server;
import com.leeroy.forwordpanel.forwordpanel.model.UserPortForward;
import com.leeroy.forwordpanel.forwordpanel.service.RemoteForwardService;
import com.leeroy.forwordpanel.forwordpanel.service.ServerService;
import com.leeroy.forwordpanel.forwordpanel.service.UserPortForwardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program: forwordpanel
 * @description: 保持中转任务
 * @author: liruifeng
 * @created: 2020/09/24 15:06
 */
@EnableScheduling
@Configurable
@Component
@Slf4j
public class KeepForwardJob {

    @Autowired
    ServerService serverService;

    @Autowired
    RemoteForwardService remoteForwardService;

    @Autowired
    UserPortForwardService userPortForwardService;

    @Scheduled(cron = "0 0/3 * * * ?")
    public void execute() {
        List<Server> serviceList = serverService.findListWithoutLogin();
        for (Server server : serviceList) {
            String lastRestart = remoteForwardService.getLastRestart(server);
            if(StringUtils.isEmpty(lastRestart)){
                server.setState(ServerStatusEnum.CONNECT_FAIL.getCode());
                serverService.save(server);
                continue;
            }else {
                server.setState(ServerStatusEnum.ONLINE.getCode());
            }
            //重启过
            if(StringUtils.isEmpty(server.getLastRebootTime()) ||!server.getLastRebootTime().equals(lastRestart)){
                log.info(">>>>重启过, 开始启动中转");
                List<UserPortForward> userForwardList = userPortForwardService.findServerEnabledForwardList(server.getId());
                for (UserPortForward userPortForward : userForwardList) {
                    userPortForwardService.startForward(userPortForward, false);
                }
                server.setLastRebootTime(lastRestart);
                log.info(">>>>重启过, 启动中转完成");
            }
            serverService.save(server);
        }
    }

}
