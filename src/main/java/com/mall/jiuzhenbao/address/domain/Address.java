package com.mall.jiuzhenbao.address.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="address")
public class Address implements Serializable {
    @Id
    @Column(name="address_id", nullable = false)
    private String addressId;

    @Column(name="address_name", nullable = false)
    private String addressName;

    @Column(name="owner_id", nullable = false)
    private String ownerId;

    @Column(name="is_default", nullable = false)
    private boolean isDefault;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
