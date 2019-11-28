package com.mall.jiuzhenbao.address.service.impl;

import com.mall.jiuzhenbao.address.dao.AddressRepository;
import com.mall.jiuzhenbao.address.domain.Address;
import com.mall.jiuzhenbao.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRep;

    public Address save(Address address){
        return addressRep.save(address);
    }

    public void update(Address address){
        addressRep.update(address);
    }
}
