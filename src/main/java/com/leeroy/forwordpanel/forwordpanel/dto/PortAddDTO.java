package com.leeroy.forwordpanel.forwordpanel.dto;

import lombok.Data;

/**
 * 端口信息
 */
@Data
public class PortAddDTO {


    /**
     * 所属服务器
     */
    private Integer serverId;

    /**
     * 本地端口
     */
    private String localPort;

    /**
     * 外网端口
     */
    private String internetPort;

}
