package com.leeroy.forwordpanel.forwordpanel.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.leeroy.forwordpanel.forwordpanel.dao.ForwardFlowDao;
import com.leeroy.forwordpanel.forwordpanel.dao.UserPortForwardDao;
import com.leeroy.forwordpanel.forwordpanel.model.ForwardFlow;
import com.leeroy.forwordpanel.forwordpanel.model.UserPortForward;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: forwordpanel
 * @description: 中转记录
 * @author: liruifeng
 * @created: 2020/09/19 20:28
 */
@Service
public class ForwardFlowService {

    @Autowired
    private ForwardFlowDao forwardFlowDao;

    @Autowired
    private UserPortForwardService userPortForwardService;

    @Autowired
    private UserPortForwardDao userPortForwardDao;

    /**
     * 保存中转记录
     * @param forwardFlow
     */
    public void saveFlow(ForwardFlow forwardFlow){
        LambdaQueryWrapper<ForwardFlow> queryWrapper = Wrappers.<ForwardFlow>lambdaQuery()
                .eq(ForwardFlow::getForwardId, forwardFlow.getForwardId())
                .eq(ForwardFlow::getRemoteIp, forwardFlow.getRemoteIp()).eq(ForwardFlow::getRemotePort, forwardFlow.getRemotePort());
        ForwardFlow oldForwardFlow = forwardFlowDao.selectOne(queryWrapper);
        //存在旧的, 更新, 否则插入
        if(oldForwardFlow!=null){
            if(forwardFlow.getDataUsage()==null||forwardFlow.getDataUsage().equals(0L)){
                return;
            }
            oldForwardFlow.setDataUsage(forwardFlow.getDataUsage());
            oldForwardFlow.setUpdateTime(new Date());
            forwardFlowDao.updateById(oldForwardFlow);
        }else {
            forwardFlow.setCreateTime(new Date());
            forwardFlowDao.insert(forwardFlow);
        }
    }

    /**
     * 收集中转流量
     * @param userForwardList
     */
    public void collectForwardFlow(List<UserPortForward> userForwardList) {
        //查询用户设置的转发
        Map<Integer, String> portFlowMap = userPortForwardService.getPortFlowMap(userForwardList);
        for (Map.Entry<Integer, String> forwardDTOStringEntry : portFlowMap.entrySet()) {
            Integer forwardId = forwardDTOStringEntry.getKey();
            UserPortForward forward = userPortForwardDao.selectById(forwardId);
            ForwardFlow forwardFlow = new ForwardFlow();
            BeanUtils.copyProperties(forward, forwardFlow);
            forwardFlow.setId(null);
            forwardFlow.setForwardId(forward.getId());
            forwardFlow.setDataUsage(Long.valueOf(forwardDTOStringEntry.getValue()));
            saveFlow(forwardFlow);
        }
    }

    /**
     * 删除用户统计
     * @param userId
     */
    public void deleteUserFlow(Integer userId) {
        LambdaQueryWrapper<ForwardFlow> queryWrapper = Wrappers.<ForwardFlow>lambdaQuery()
                .eq(ForwardFlow::getUserId, userId);
        forwardFlowDao.delete(queryWrapper);
    }

    /**
     * 获取用户流量
     * @param userId
     * @return
     */
    public Long getUserFlowTotal(Integer userId){
        LambdaQueryWrapper<ForwardFlow> queryWrapper = Wrappers.<ForwardFlow>lambdaQuery()
                .eq(ForwardFlow::getUserId, userId);
        List<ForwardFlow> forwardFlows = forwardFlowDao.selectList(queryWrapper);
        return forwardFlows.stream().collect(Collectors.summingLong(ForwardFlow::getDataUsage));
    }

}
