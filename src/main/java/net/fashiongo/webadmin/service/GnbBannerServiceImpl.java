package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.data.entity.primary.GnbMenuBannerTypeEntity;
import net.fashiongo.webadmin.data.repository.primary.GnbMenuBannerTypeEntityRepository;
import net.fashiongo.webadmin.model.pojo.response.GnbBannerTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GnbBannerServiceImpl implements GnbBannerService {

	private final GnbMenuBannerTypeEntityRepository gnbMenuBannerTypeEntityRepository;

	private final String bannerImageUrl;

	@Autowired
	public GnbBannerServiceImpl(GnbMenuBannerTypeEntityRepository gnbMenuBannerTypeEntityRepository,
								@Value("${toast.cloud.storage.object-storage.api-url}") String objectStorageUrl,
								@Value("${toast.cloud.storage.object-storage.account}") String objectStorageAuth,
								@Value("${gnb.banner.image.storage.directory}") String bannerDirectory) {
		this.gnbMenuBannerTypeEntityRepository = gnbMenuBannerTypeEntityRepository;
		this.bannerImageUrl = objectStorageUrl + objectStorageAuth + "/" + bannerDirectory;
	}

	@Override
	public List<GnbBannerTypeResponse> getAllTypes() {
		List<GnbMenuBannerTypeEntity> types = gnbMenuBannerTypeEntityRepository.getList();
		return types.stream().map(e -> GnbBannerTypeResponse.convertFrom(e, bannerImageUrl)).collect(Collectors.toList());
	}

	@Override
	public Optional<GnbBannerTypeResponse> getType(int id) {
		return gnbMenuBannerTypeEntityRepository.getOneFromId(id).map(e -> GnbBannerTypeResponse.convertFrom(e, bannerImageUrl));
	}
}
