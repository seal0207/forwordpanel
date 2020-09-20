package com.leeroy.forwordpanel.forwordpanel.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @program: forwordpanel
 * @description: 用户中转流量记录
 * @author: liruifeng
 * @created: 2020/09/17 14:06
 */
@Entity
@Data
public class ForwardFlow {

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    //中转记录id
    private Integer forwardId;
    // 中转用户
    private Integer userId;
    //端口id
    private Integer portId;
    // 服务id
    private Integer serverId;
    // 目标ip
    private String remoteIp;
    // 目标主机地址
    private String remoteHost;
    // 流量使用量
    private Long dataUsage;
    // 目标主机端口
    private Integer remotePort;
    // 创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //删除表示
    private Boolean deleted;
    //禁用表示
    private Boolean disabled;
}
