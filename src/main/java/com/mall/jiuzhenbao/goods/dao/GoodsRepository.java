package com.mall.jiuzhenbao.goods.dao;

import com.mall.jiuzhenbao.goods.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GoodsRepository extends JpaRepository<Goods, String>, JpaSpecificationExecutor<Goods> {
}
