package com.leeroy.forwordpanel.forwordpanel.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.leeroy.forwordpanel.forwordpanel.common.WebCurrentData;
import com.leeroy.forwordpanel.forwordpanel.common.response.ApiResponse;
import com.leeroy.forwordpanel.forwordpanel.dao.*;
import com.leeroy.forwordpanel.forwordpanel.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: forwordpanel
 * @description: 数据升级服务
 * @author: liruifeng
 * @created: 2020/09/20 13:41
 */
@Slf4j
@Service
public class DataUpgradeService {

    @Autowired
    private ServerDao serverDao;

    @Autowired
    private UserServerDao userServerDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserPortDao userPortDao;

    @Autowired
    private PortDao portDao;

    @Autowired
    private ClashDao clashDao;

    @Autowired
    private UserPortForwardDao userPortForwardDao;

    @Autowired
    private ForwardFlowDao forwardFlowDao;


    /**
     * 导出数据
     * @return
     */
    public ApiResponse exportData(){
        if(WebCurrentData.getUser().getUserType()>0){
            return ApiResponse.error("403", "您没有权限导出数据");
        }
        Map<String, Object> exportMap = new HashMap<>();
        //server list
        List<Server> serverList = serverDao.selectList(Wrappers.lambdaQuery());
        exportMap.put("serverList", serverList);
        //user list
        List<User> userList = userDao.selectList(Wrappers.lambdaQuery());
        exportMap.put("userList", userList);
        //user port list
        List<UserPort> userPortList = userPortDao.selectList(Wrappers.lambdaQuery());
        exportMap.put("userPortList", userPortList);
        //user port list
        List<Port> portList = portDao.selectList(Wrappers.lambdaQuery());
        exportMap.put("portList", portList);
        //clash list
        List<Clash> clashList = clashDao.selectList(Wrappers.lambdaQuery());
        exportMap.put("clashList", clashList);
        //forward list
        List<UserPortForward> forwardList = userPortForwardDao.selectList(Wrappers.lambdaQuery());
        exportMap.put("forwardList", forwardList);
        try {
            //forward flow list
            List<ForwardFlow> forwardFlowList = forwardFlowDao.selectList(Wrappers.lambdaQuery());
            exportMap.put("forwardFlowList", forwardFlowList);
        }catch (Exception e){
            log.error("导出{}失败", "forwardFlowList");
        }
        try {
            //user server list
            List<UserServer> userServerList = userServerDao.selectList(Wrappers.lambdaQuery());
            exportMap.put("userServerList", userServerList);
        } catch (Exception e) {
            log.error("导出{}失败", "userServerList");
        }
        return ApiResponse.ok(exportMap);
    }

    /**
     * 导入json
     * @param file
     * @return
     */
    public ApiResponse importData(MultipartFile file){
        if(WebCurrentData.getUser().getUserType()>0){
            return ApiResponse.error("403", "您没有权限导入数据");
        }
        try {
            String json = IOUtils.toString(file.getInputStream(), "UTF-8");
            JSONObject jsonObject = JSON.parseObject(json);
            //server
            JSONArray serverList = jsonObject.getJSONArray("serverList");
            for (int i = 0; i < serverList.size(); i++) {
                Server server = serverList.getObject(i, Server.class);
                serverDao.insert(server);
            }
            //user
            JSONArray userList = jsonObject.getJSONArray("userList");
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.getObject(i, User.class);
                userDao.insert(user);
            }
            //user port
            JSONArray userPortList = jsonObject.getJSONArray("userPortList");
            for (int i = 0; i < userPortList.size(); i++) {
                UserPort userPort = userPortList.getObject(i, UserPort.class);
                userPortDao.insert(userPort);
            }
            //port
            JSONArray portList = jsonObject.getJSONArray("portList");
            for (int i = 0; i < portList.size(); i++) {
                Port port = portList.getObject(i, Port.class);
                portDao.insert(port);
            }
            //clash
            JSONArray clashList = jsonObject.getJSONArray("clashList");
            for (int i = 0; i < clashList.size(); i++) {
                Clash clash = clashList.getObject(i, Clash.class);
                clashDao.insert(clash);
            }
            //forward
            JSONArray forwardList = jsonObject.getJSONArray("forwardList");
            for (int i = 0; i < forwardList.size(); i++) {
                UserPortForward userPortForward = clashList.getObject(i, UserPortForward.class);
                userPortForwardDao.insert(userPortForward);
            }
            //user server
            JSONArray userServerList = jsonObject.getJSONArray("userServerList");
            for (int i = 0; i < userServerList.size(); i++) {
                UserServer userServer = userServerList.getObject(i, UserServer.class);
                userServerDao.insert(userServer);
            }
            try {
                //forward flow
                JSONArray forwardFlowList = jsonObject.getJSONArray("forwardFlowList");
                for (int i = 0; i < forwardFlowList.size(); i++) {
                    ForwardFlow forwardFlow = clashList.getObject(i, ForwardFlow.class);
                    forwardFlowDao.insert(forwardFlow);
                }
            } catch (Exception e) {
                log.error("导入{}失败", "forward flow");
            }
        }catch (Exception e){
            log.info("导入失败", e);
        }
        return ApiResponse.ok();
    }


}
