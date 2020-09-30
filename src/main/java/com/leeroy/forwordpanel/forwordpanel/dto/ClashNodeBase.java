package com.leeroy.forwordpanel.forwordpanel.dto;

import lombok.Data;

/**
 * @program: forwordpanel
 * @description: clash node
 * @author: liruifeng
 * @created: 2020/09/30 14:01
 */
@Data
public class ClashNodeBase {

    protected String name;
    protected String server;
    protected String port;
    protected String type;
    protected String password;
}
