package com.leeroy.forwordpanel.forwordpanel.dto;

import lombok.Data;

/**
 * @program: forwordpanel
 * @description: 端口分页请求
 * @author: liruifeng
 * @created: 2020/10/14 12:51
 */
@Data
public class PortPageRequest extends PageRequest{

    private Integer serverId;
}
