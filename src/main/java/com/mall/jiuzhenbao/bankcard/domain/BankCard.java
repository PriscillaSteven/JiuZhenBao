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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
