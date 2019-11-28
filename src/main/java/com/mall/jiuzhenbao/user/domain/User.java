package com.mall.jiuzhenbao.user.domain;

import com.mall.jiuzhenbao.base.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {
    @Id
    @Column(name="user_id", nullable=false)
    private String userId;

    @Column(name="user_name", length=128)
    private String username;

    @Column(name="password", length=128, nullable=false)
    private String password;

    @Column(name="phone", length=11)
    private String phone;

    @Column(name="photo_path", length=256)
    private String photoPath;

    @Column(name="create_by")
    private String createBy;

    @Column(name="buy_level", nullable=false)
    private int buyLevel;

    @Column(name="consume_level", nullable=false)
    private int consume_level;

    @Column(name="team_level", nullable=false)
    private int team_level;

    @Column(name="remaining_bean", nullable=false)
    private int remainingBean;

    @Column(name="remaining_score", nullable=false)
    private int remainingScore;

    @Column(name="consume_score", nullable = false)
    private int consumeScore;

    @Column(name="try_login_times")
    private int tryLoginTimes;

    @Column(name="is_valid", nullable=false)
    private boolean isValid;

    @Column(name="register_date", nullable=false)
    private Date registerDate;

    @Column(name="is_deleted")
    private boolean isDeleted = false;

    @Column(name="last_login_time", nullable = false)
    private Date lastLoginTime;

    @Column(name="login_failure_time", nullable = false)
    private int loginFailureTime;

    @Column(name="login_ip", nullable = false)
    private String loginIp;

    private Set<BankCard> cards;

    private List<Coupon> coupons;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public int getBuyLevel() {
        return buyLevel;
    }

    public void setBuyLevel(int buyLevel) {
        this.buyLevel = buyLevel;
    }

    public int getConsume_level() {
        return consume_level;
    }

    public void setConsume_level(int consume_level) {
        this.consume_level = consume_level;
    }

    public int getTeam_level() {
        return team_level;
    }

    public void setTeam_level(int team_level) {
        this.team_level = team_level;
    }

    public int getRemainingBean() {
        return remainingBean;
    }

    public void setRemainingBean(int remainingBean) {
        this.remainingBean = remainingBean;
    }

    public int getRemainingScore() {
        return remainingScore;
    }

    public void setRemainingScore(int remainingScore) {
        this.remainingScore = remainingScore;
    }

    public int getConsumeScore() {
        return consumeScore;
    }

    public void setConsumeScore(int consumeScore) {
        this.consumeScore = consumeScore;
    }

    public int getTryLoginTimes() {
        return tryLoginTimes;
    }

    public void setTryLoginTimes(int tryLoginTimes) {
        this.tryLoginTimes = tryLoginTimes;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getLoginFailureTime() {
        return loginFailureTime;
    }

    public void setLoginFailureTime(int loginFailureTime) {
        this.loginFailureTime = loginFailureTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Set<BankCard> getCards() {
        return cards;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public void setCards(Set<BankCard> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password);
    }
}
