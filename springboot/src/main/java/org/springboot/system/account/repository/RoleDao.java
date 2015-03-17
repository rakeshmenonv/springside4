package org.springboot.system.account.repository;

import java.util.List;






import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springboot.system.account.entity.Role;
@Repository("roleDao")
public interface RoleDao extends PagingAndSortingRepository<Role, Long>, RoleDaoCustom, JpaSpecificationExecutor<Role> {
	Role findByName(String name);

	List<Role> findRoleByRoleType(String value);
	
}
