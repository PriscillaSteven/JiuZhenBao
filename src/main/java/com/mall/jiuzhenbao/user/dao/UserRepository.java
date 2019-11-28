package com.mall.jiuzhenbao.user.dao;

import com.mall.jiuzhenbao.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Domain repository for {@link User}.
 * Extending the JpaRepository interface provided by spring data jpa.
 * Created by Steven
 * @version 0.0.1
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    @Query("SELECT u FROM User u WHERE u.username='zhangsan'")
    public User getLoginUser();

    public List<User> findByUsername(String username);
}
