package com.mall.jiuzhenbao.address.dao;

import com.mall.jiuzhenbao.address.domain.Address;
import com.mall.jiuzhenbao.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Domain repository for {@link User}.
 * Extending the JpaRepository interface provided by spring data jpa.
 * Created by Steven
 * @version 0.0.1
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, String>, JpaSpecificationExecutor<Address> {
    public void update(Address address);
}
