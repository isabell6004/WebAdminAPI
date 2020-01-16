/**
 * 
 */
package net.fashiongo.webadmin.service.sitemgmt;

import net.fashiongo.webadmin.data.entity.primary.sitemgmt.SocialMedia;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author roy
 *
 */
@Service
public interface SocialMediaNewService {

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void delete(List<Long> delIds);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void regist(SocialMedia socialMedia);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void update(SocialMedia socialMedia);
}
