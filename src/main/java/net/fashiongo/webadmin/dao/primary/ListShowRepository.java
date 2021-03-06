/**
 * 
 */
package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.show.ListShow;

/**
 * @author Sanghyup
 *
 */
public interface ListShowRepository extends CrudRepository<ListShow, Integer> {
	// find all
	List<ListShow> findAll();

	// findAllOrderByShowNameAsc
	List<ListShow> findAllByOrderByShowNameAsc();

	// findOneByShowID
	ListShow findOneByShowID(Integer showID);

	// find by showid
	List<ListShow> findByShowID(Integer showID);

	// find top 1 order by showid desc
	// for getting the maximum number of key
	ListShow findTopByOrderByShowIDDesc();

	List<ListShow> findByActiveOrderByShowName(boolean active);
}
