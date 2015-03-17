package org.springboot.system.area.repository;


import org.springboot.system.area.entity.Province;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProvinceDao extends PagingAndSortingRepository<Province, Long>, JpaSpecificationExecutor<Province>  {
      Province findByProvinceId(String provinceId);
}