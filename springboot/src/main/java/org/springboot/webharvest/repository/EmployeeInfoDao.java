package org.springboot.webharvest.repository;

import org.springboot.webharvest.entity.EmployeeInfo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public interface EmployeeInfoDao extends PagingAndSortingRepository<EmployeeInfo, Long>, JpaSpecificationExecutor<EmployeeInfo>  {

}