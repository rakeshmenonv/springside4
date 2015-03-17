package org.springboot.system.area.repository;

import java.util.List;

import org.springboot.system.area.entity.County;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CountyDao extends PagingAndSortingRepository<County, Long>,
		JpaSpecificationExecutor<County> {

	@Query("from County c where c.city.cityId =?1")
	List<County> findByCityCityId(String cityId);

	County findCountyByCountyId(String countyId);

	List<County> findByCityId(Long cityId);

	County findByCountyId(String entCounty);

	County findCountyByCounty(String county);

}