package com.mall.jiuzhenbao.bankcard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "bank_card")
public class BankCard implements Serializable {
    @Id
    @Column(name="card_id", nullable=false)
    private String cardId;

    @Column(name="card_name", nullable = false)
    private String cardName;

    @Column(name="card_number", nullable = false)
    private String cardNumber;

    @Column(name="photo_url", nullable=false)
    private String photoUrl;

    @Column(name="type", nullable = false)
    private int type;

    @Column(name="owner_id", nullable = false)
    private String ownerId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
}
