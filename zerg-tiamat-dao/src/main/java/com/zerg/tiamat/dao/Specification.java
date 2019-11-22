package com.zerg.tiamat.dao;

import java.util.Date;

public class Specification {
    private Integer id;

    private Date createdAt;

    private Date updatedAt;

    private Integer createdBy;

    private Integer updatedBy;

    private Byte deleted;

    private Integer statementId;

    private Integer market;

    private String symbol;

    private Integer interval;

    private Byte type;

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

    public Integer getStatementId() {
        return statementId;
    }

    public void setStatementId(Integer statementId) {
        this.statementId = statementId;
    }

    public Integer getMarket() {
        return market;
    }

    public void setMarket(Integer market) {
        this.market = market;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}