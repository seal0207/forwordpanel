package com.leeroy.forwordpanel.forwordpanel.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 端口信息
 */
@Entity
@Data
public class Port {

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属服务器
     */
    private Integer serverId;

    /**
     * 本地端口
     */
    private Integer localPort;

    /**
     * 外网端口
     */
    private Integer internetPort;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}
