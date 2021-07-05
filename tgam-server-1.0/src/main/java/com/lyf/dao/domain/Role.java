package com.lyf.dao.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Role implements Serializable {

    private static final long serialVersionUID = 4509515236219572349L;

    private Integer id;
    private String roleId;
    private String name;
    private String description;
    private Integer status;
    private Timestamp createTime; // 可以省略？？管理员可以更新角色
    private Timestamp updateTime;


    public Role() {
    }

    public Role(Integer id, String roleId, String name, String description, Integer status, Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.roleId = roleId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
        return "Role{" +
                "id=" + id +
                ", roleId='" + roleId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
