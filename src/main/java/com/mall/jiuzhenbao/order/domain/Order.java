package com.mall.jiuzhenbao.order.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="order")
public class Order implements Serializable {
    @Column(name="order_id", nullable =  false)
    private String orderId;
    @Column(name="order_name", nullable =  false)
    private String orderName;
    @Column(name="order_price", nullable =  false)
    private double orderPrice;
    @Column(name="goods_count", nullable =  false)
    private int goodsCount;
    @Column(name="owner_id", nullable =  false)
    private String ownerId;
    @Column(name="type", nullable =  false)
    private int type;
    @Column(name="original_price_pay_time", nullable =  false)
    private Date originalPricePayTime;
    @Column(name="batch_price_pay_time", nullable =  false)
    private Date batchPricePayTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getOriginalPricePayTime() {
        return originalPricePayTime;
    }

    public void setOriginalPricePayTime(Date originalPricePayTime) {
        this.originalPricePayTime = originalPricePayTime;
    }

    public Date getBatchPricePayTime() {
        return batchPricePayTime;
    }

    public void setBatchPricePayTime(Date batchPricePayTime) {
        this.batchPricePayTime = batchPricePayTime;
    }
}

