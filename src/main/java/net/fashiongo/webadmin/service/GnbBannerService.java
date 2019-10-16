package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.model.pojo.response.GnbBannerTypeResponse;

import java.util.List;
import java.util.Optional;

public interface GnbBannerService {
	List<GnbBannerTypeResponse> getAllTypes();

	Optional<GnbBannerTypeResponse> getType(int id);

}
