package com.mall.jiuzhenbao.coupon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="coupon")
public class Coupon implements Serializable {
    @Id
    @Column(name="coupon_id", nullable = false)
    private String couponId;

    @Column(name="coupon_name", nullable = false)
    private String couponName;

    @Column(name="owner_id", nullable = false)
    private String ownerId;

    @Column(name="original_price", nullable = false)
    private double originalPrice;

    @Column(name="discount_price", nullable = false)
    private double discountPrice;

    @Column(name="create_time", nullable = false)
    private Date createTime;

    @Column(name="wine_name", nullable = false)
    private String wineName;

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }
}
