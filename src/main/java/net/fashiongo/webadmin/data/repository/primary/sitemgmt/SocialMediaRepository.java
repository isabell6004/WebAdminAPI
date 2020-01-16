package net.fashiongo.webadmin.data.repository.primary.sitemgmt;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.data.entity.primary.sitemgmt.SocialMedia;

/**
 * @author roy
 */
public interface SocialMediaRepository extends CrudRepository<SocialMedia, Integer> {
	List<SocialMedia> findAllByOrderBySocialMediaIdAsc();
}
