package com.zerg.tiamat.dao;

import java.util.Date;

public class DataModel {
    private Integer id;

    private Date createdAt;

    private Date updatedAt;

    private Integer createdBy;

    private Integer updatedBy;

    private Byte deleted;

    private Integer userId;

    private Integer strategyId;

    private String name;

    private String describe;

    private String path;

    private Integer requestMethod;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(Integer requestMethod) {
        this.requestMethod = requestMethod;
    }
}