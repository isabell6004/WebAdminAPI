package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityMenu;;

public interface SecurityMenuRepository extends CrudRepository<SecurityMenu, Integer> {
	SecurityMenu findOneByRoutePath(String pageName);
	List<SecurityMenu> findAllByParentIDAndApplicationIDOrderByActiveDescNameAsc(String ParentID,Integer ApplicationID);
	SecurityMenu findOneByResourceID(Integer ResourceID);
	SecurityMenu findOneByResourceIDAndMenuIDNot(Integer ResourceID,Integer MenuID);
	SecurityMenu findOneByMenuID(Integer MenuID);
}
