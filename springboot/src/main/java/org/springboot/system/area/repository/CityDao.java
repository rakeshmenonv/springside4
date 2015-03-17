package org.springboot.system.area.repository;


import java.util.List;

import org.springboot.system.area.entity.City;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CityDao extends PagingAndSortingRepository<City, Long>, JpaSpecificationExecutor<City>  {

	City findCityByCityId(String cityId);
	
	@Query("from City c where c.province.provinceId =?1")
	List<City> getProvinceId(String provinceId);
	City findByCityId(String entCity);
	
	
}