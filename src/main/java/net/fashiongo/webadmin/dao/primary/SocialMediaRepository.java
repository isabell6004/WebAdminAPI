package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SocialMedia;

/**
 * @author roy
 */
public interface SocialMediaRepository extends CrudRepository<SocialMedia, Integer> {
	public List<SocialMedia> findAllByOrderBySocialMediaIdAsc();
	
//	public <T> List<T> findAllBy(Class<T> type);
}
