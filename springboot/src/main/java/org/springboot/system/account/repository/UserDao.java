package org.springboot.system.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springboot.system.account.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long>,
		JpaSpecificationExecutor<User> {
	@Query("from User where loginName=?1")
	User findByLoginName(String loginName);

	List<User> findByUserType(int i);

}
