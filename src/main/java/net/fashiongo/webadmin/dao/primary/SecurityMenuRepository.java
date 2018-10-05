package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityMenu;;

public interface SecurityMenuRepository extends CrudRepository<SecurityMenu, Integer> {
	SecurityMenu findOneByRoutePath(String pageName);
}
