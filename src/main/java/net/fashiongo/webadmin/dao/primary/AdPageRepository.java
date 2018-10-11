package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdPage;

/**
 * @author Nayeon Kim
 */
public interface AdPageRepository extends CrudRepository<AdPage, Integer> {
	AdPage findOneByPageID(Integer PageID);

	List<AdPage> findAllByOrderByPageIDDesc();
	
	// select top 1 order by pageid desc
	AdPage findTopByOrderByPageIDDesc(); // select top 1 * from ad_page order by pageid desc
	
	//@Modifying
	//@Query("select PageID, PageName FROM Ad_Page t1 WHERE EXISTS(SELECT NULL FROM Ad_PageSpot t2 WHERE (((t1.PageID = t2.PageID) AND (t2.Active = 1)) AND (t2.BidEffectiveOn <= CAST(GETDATE() as DATE))))")
	//List<AdPage> findAll();
	
	//List<AdPage> findByAdPageSpot();
}
