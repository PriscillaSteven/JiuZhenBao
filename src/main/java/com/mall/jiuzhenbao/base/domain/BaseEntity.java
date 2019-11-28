package com.mall.jiuzhenbao.base.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseEntity {
    @Column(name="create_ts", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Column(name="modify_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
