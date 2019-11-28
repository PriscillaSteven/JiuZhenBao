package com.mall.jiuzhenbao.bankcard.service.impl;

import com.mall.jiuzhenbao.bankcard.dao.BankCardRepository;
import com.mall.jiuzhenbao.bankcard.domain.BankCard;
import com.mall.jiuzhenbao.bankcard.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;

public class BankCardServiceImpl implements BankCardService {
    @Autowired
    private BankCardRepository bankCardRep;

    public BankCard save(BankCard bankCard){
        return bankCardRep.save(bankCard);
    }
}
