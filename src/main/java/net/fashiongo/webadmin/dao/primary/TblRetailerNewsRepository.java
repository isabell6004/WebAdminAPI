package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.TblRetailerNews;

/**
 * 
 * @author Reo
 *
 */
public interface TblRetailerNewsRepository extends CrudRepository<TblRetailerNews, Integer> {
	TblRetailerNews findOneByNewsID(Integer newsID);
	void deleteByNewsIDIn(List<Integer> newsID);
}
