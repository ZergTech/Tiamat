package com.zerg.tiamat.dao;

import java.util.Date;

public class Statement {
    private Integer id;

    private Date createdAt;

    private Date updatedAt;

    private Integer createdBy;

    private Integer updatedBy;

    private Byte deleted;

    private Integer dataModelId;

    private String describe;

    private Byte property;

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

    public Integer getDataModelId() {
        return dataModelId;
    }

    public void setDataModelId(Integer dataModelId) {
        this.dataModelId = dataModelId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Byte getProperty() {
        return property;
    }

    public void setProperty(Byte property) {
        this.property = property;
    }
}