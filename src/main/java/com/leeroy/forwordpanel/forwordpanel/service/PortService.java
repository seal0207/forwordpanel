package com.leeroy.forwordpanel.forwordpanel.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leeroy.forwordpanel.forwordpanel.common.response.ApiResponse;
import com.leeroy.forwordpanel.forwordpanel.dao.PortDao;
import com.leeroy.forwordpanel.forwordpanel.dao.ServerDao;
import com.leeroy.forwordpanel.forwordpanel.dao.UserPortDao;
import com.leeroy.forwordpanel.forwordpanel.dto.PageRequest;
import com.leeroy.forwordpanel.forwordpanel.dto.PortDTO;
import com.leeroy.forwordpanel.forwordpanel.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PortService {

    @Autowired
    private PortDao portDao;

    @Autowired
    private ServerDao serverDao;

    @Autowired
    private UserPortDao userPortDao;


    /**
     * 保存clash
     */
    public ApiResponse save(Port clash) {
        if (StringUtils.isEmpty(clash.getId())) {
            clash.setCreateTime(new Date());
            clash.setDeleted(false);
            portDao.insert(clash);
        } else {
            Port existPort = portDao.selectById(clash.getId());
            BeanUtils.copyProperties(clash, existPort);
            existPort.setUpdateTime(new Date());
            portDao.updateById(existPort);
        }
        return ApiResponse.ok();
    }


    /**
     * 查询clash列表
     *
     * @return
     */
    public PageInfo findList(PageRequest pageRequest) {
        LambdaQueryWrapper<Port> queryWrapper = Wrappers.<Port>lambdaQuery().eq(Port::getDeleted, false);
        Page page = PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Port> portList = portDao.selectList(queryWrapper);
        List<PortDTO> portDTOList = new ArrayList<>();
        for (Port port : portList) {
            PortDTO portDTO = new PortDTO();
            BeanUtils.copyProperties(port, portDTO);
            Server server = serverDao.selectById(port.getServerId());
            if(server!=null){
                portDTO.setServerName(server.getServerName());
                portDTO.setServerHost(server.getHost());
            }
            portDTOList.add(portDTO);
        }
        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(portDTOList);
        return pageInfo;
    }

    /**
     * 查询clash列表
     *
     * @return
     */
    public List<PortDTO> findFreePortList() {
        //todo serverId权限校验
        LambdaQueryWrapper<Port> queryWrapper = Wrappers.<Port>lambdaQuery().eq(Port::getDeleted, false);
        List<Port> portList = portDao.selectList(queryWrapper);
        //查询出已经占用的端口
        LambdaQueryWrapper<UserPort> userPortQueryWrapper = Wrappers.<UserPort>lambdaQuery().eq(UserPort::getDeleted, false);
        List<UserPort> userPortList = userPortDao.selectList(userPortQueryWrapper);
        //过滤掉已经分配的端口
        List<Port> result = portList.stream().filter(port -> {
            for (UserPort userPort : userPortList) {
                if (userPort.getPortId().equals(port.getId())) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
        List<PortDTO> portDTOList = new ArrayList<>();
        for (Port port : result) {
            PortDTO portDTO = new PortDTO();
            BeanUtils.copyProperties(port, portDTO);
            Server server = serverDao.selectById(port.getServerId());
            portDTO.setServerName(server.getServerName());
            portDTOList.add(portDTO);
        }
        return portDTOList;
    }

    /**
     * 删除clash
     */
    public ApiResponse delete(Integer id) {
        LambdaQueryWrapper<UserPort> queryWrapper = Wrappers.<UserPort>lambdaQuery().eq(UserPort::getPortId, id)
                .eq(UserPort::getDeleted, false);
        List<UserPort> userPorts = userPortDao.selectList(queryWrapper);
        if(!CollectionUtils.isEmpty(userPorts)){
            return ApiResponse.error("403", "端口已授权给用户, 请先删除");
        }
        Port userPort = new Port();
        userPort.setId(id);
        userPort.setDeleted(true);
        portDao.updateById(userPort);
        return ApiResponse.ok();
    }

}
