package com.mall.jiuzhenbao.bankcard.dao;

import com.mall.jiuzhenbao.bankcard.domain.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BankCardRepository extends JpaRepository<BankCard, String>, JpaSpecificationExecutor<BankCard> {
}
