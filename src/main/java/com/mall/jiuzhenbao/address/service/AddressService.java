package com.mall.jiuzhenbao.address.service;

import com.mall.jiuzhenbao.address.domain.Address;

public interface AddressService {
    public Address save(Address address);

    public void update(Address address);
}
