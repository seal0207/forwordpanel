package com.leeroy.forwordpanel.forwordpanel.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program: forwordpanel
 * @description: 保持中转任务
 * @author: liruifeng
 * @created: 2020/09/24 15:06
 */
@Component
public class KeepForwardJob {

    @Scheduled(cron = "0 0/5 * * * ?")
    public void execute() {

    }

}
