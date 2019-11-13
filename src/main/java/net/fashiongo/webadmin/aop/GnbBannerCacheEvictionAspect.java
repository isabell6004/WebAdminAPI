package net.fashiongo.webadmin.aop;

import net.fashiongo.webadmin.service.CacheService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class GnbBannerCacheEvictionAspect {

	private final CacheService cacheService;

	@Autowired
	public GnbBannerCacheEvictionAspect(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	@AfterReturning("execution(* net.fashiongo.webadmin.service.GnbBannerService.add*(..)) || execution(* net.fashiongo.webadmin.service.GnbBannerService.modify*(..)) || execution(* net.fashiongo.webadmin.service.GnbBannerService.remove*(..))")
	public void evictCache() throws Throwable {
		cacheService.GetRedisCacheEvict("GnbMenuBanner", null);
	}

}
