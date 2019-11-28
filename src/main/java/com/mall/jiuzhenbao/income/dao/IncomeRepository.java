package com.mall.jiuzhenbao.income.dao;

import com.mall.jiuzhenbao.income.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IncomeRepository extends JpaRepository<Income, String>, JpaSpecificationExecutor<Income> {
}
