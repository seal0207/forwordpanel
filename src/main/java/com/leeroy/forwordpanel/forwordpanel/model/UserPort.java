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
 * 端口
 */
@Entity
@Data
public class UserPort {

    @Id
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer portId;
    private Integer serverId;
    private Boolean disabled;
    private Boolean deleted;
    /**
     * 端口流量限制
     */
    private Long dataLimit;
    /**
     * 到期时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expireTime;

    private Date createTime;

    private Date updateTime;
}
