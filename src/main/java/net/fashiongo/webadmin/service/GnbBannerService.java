package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.model.pojo.response.GnbBannerResponse;
import net.fashiongo.webadmin.model.pojo.response.GnbBannerTypeResponse;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface GnbBannerService {
	List<GnbBannerTypeResponse> getAllTypes();

	Optional<GnbBannerTypeResponse> getType(int id);

	GnbBannerResponse addBanner(int bannerTypeId, String imageFileName, InputStream inputStream, String targetUrl);

	GnbBannerResponse modifyBanner(int bannerTypeId, int bannerId, String imageFileName, InputStream inputStream, String targetUrl);

	GnbBannerResponse modifyTargetUrl(int bannerTypeId, int bannerId, String targetUrl);
}
