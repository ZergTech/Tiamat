package com.zerg.tiamat.dao;

import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode
public class MonitorTask {
    private Integer id;

    private Date crreatedAt;

    private Date updatedAt;

    private Integer deleted;

    private String symbol;

    private String timeInterval;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCrreatedAt() {
        return crreatedAt;
    }

    public void setCrreatedAt(Date crreatedAt) {
        this.crreatedAt = crreatedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval == null ? null : timeInterval.trim();
    }
}