package com.leeroy.forwordpanel.forwordpanel.model;

import lombok.Data;

import java.util.List;

/**
 * 资源
 */
@Data
public class Resource {

    private Integer id;

    private String title;

    private String path;

    private boolean meta;

    private boolean hidden;

    private String icon;

    private List<Resource> children;

    public Resource(Integer id, String title, String path, boolean meta, boolean hidden) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.meta = meta;
        this.hidden = hidden;
    }

    public Resource(Integer id, String title, String path, String icon) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.meta = true;
        this.hidden = false;
        this.icon = icon;
    }
}
