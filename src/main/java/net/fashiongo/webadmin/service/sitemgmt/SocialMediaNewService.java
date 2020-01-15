/**
 * 
 */
package net.fashiongo.webadmin.service.sitemgmt;

import net.fashiongo.webadmin.model.primary.SocialMedia;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author roy
 *
 */
@Service
public interface SocialMediaNewService {

    void delete(List<Long> delIds);

    void registOrUpdate(SocialMedia socialMedia);
}
