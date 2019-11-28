package com.mall.jiuzhenbao.income.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="income")
public class Income implements Serializable {
    @Id
    @Column(name="income_id")
    private String incomeId;

    @Column(name="type", nullable = false)
    private int type;

    @Column(name="owner_id", nullable = false)
    private String ownerId;

    @Column(name="owner_name", nullable = false)
    private String ownerName;

    @Column(name="original_order_create_time")
    private Date originalOrderCreateTime;

    @Column(name="batch_order_create_time")
    private Date batchOrderCreateTime;

    @Column(name="order_price")
    private double orderPrice;

    @Column(name="commission")
    private double commission;

    public String getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(String incomeId) {
        this.incomeId = incomeId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getOriginalOrderCreateTime() {
        return originalOrderCreateTime;
    }

    public void setOriginalOrderCreateTime(Date originalOrderCreateTime) {
        this.originalOrderCreateTime = originalOrderCreateTime;
    }

    public Date getBatchOrderCreateTime() {
        return batchOrderCreateTime;
    }

    public void setBatchOrderCreateTime(Date batchOrderCreateTime) {
        this.batchOrderCreateTime = batchOrderCreateTime;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
}
