package com.lyf.dao.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Permission implements Serializable {
    private static final long serialVersionUID = 4617743610484642981L;
    private Long id;
    private String permissionId;
    private String name;
    private String description;
    private String url;
    private String perms;
    private Long parent_id;
    private Integer type;
    private Integer orderNum;
    private String icon;
    private Integer status;
    private Timestamp createTime;
    private Timestamp updateTime;


    public Permission() {
    }

    public Permission(Long id, String permissionId, String name, String description, String url, String perms, Long parent_id, Integer type, Integer orderNum, String icon, Integer status, Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.permissionId = permissionId;
        this.name = name;
        this.description = description;
        this.url = url;
        this.perms = perms;
        this.parent_id = parent_id;
        this.type = type;
        this.orderNum = orderNum;
        this.icon = icon;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionId='" + permissionId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", perms='" + perms + '\'' +
                ", parent_id=" + parent_id +
                ", type=" + type +
                ", orderNum=" + orderNum +
                ", icon='" + icon + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
