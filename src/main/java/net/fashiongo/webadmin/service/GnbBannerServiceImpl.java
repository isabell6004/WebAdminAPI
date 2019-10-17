package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.data.entity.primary.GnbMenuBannerEntity;
import net.fashiongo.webadmin.data.entity.primary.GnbMenuBannerTypeEntity;
import net.fashiongo.webadmin.data.repository.primary.GnbMenuBannerEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.GnbMenuBannerTypeEntityRepository;
import net.fashiongo.webadmin.model.pojo.response.GnbBannerResponse;
import net.fashiongo.webadmin.model.pojo.response.GnbBannerTypeResponse;
import net.fashiongo.webadmin.support.storage.SwiftApiCallFactory;
import net.fashiongo.webadmin.utility.Utility;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GnbBannerServiceImpl implements GnbBannerService {

	private final GnbMenuBannerTypeEntityRepository gnbMenuBannerTypeEntityRepository;

	@Autowired
	private GnbMenuBannerEntityRepository gnbMenuBannerEntityRepository;

	private final SwiftApiCallFactory factory;

	private final String bannerImageUrl;

	private final String bannerContainerName;

	@Autowired
	public GnbBannerServiceImpl(GnbMenuBannerTypeEntityRepository gnbMenuBannerTypeEntityRepository,
								SwiftApiCallFactory factory,
								@Value("${toast.cloud.storage.object-storage.api-url}") String objectStorageUrl,
								@Value("${toast.cloud.storage.object-storage.account}") String objectStorageAuth,
								@Value("${gnb.banner.image.storage.directory}") String bannerDirectory) {
		this.gnbMenuBannerTypeEntityRepository = gnbMenuBannerTypeEntityRepository;
		this.bannerContainerName = bannerDirectory;
		this.bannerImageUrl = objectStorageUrl + objectStorageAuth + "/" + bannerDirectory;
		this.factory = factory;
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

	@Override
	public GnbBannerResponse addBanner(int bannerTypeId, String imageFileName, InputStream inputStream, String targetUrl) {
		GnbMenuBannerTypeEntity typeEntity = gnbMenuBannerTypeEntityRepository.getOneFromId(bannerTypeId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid banner type ID."));
		CloseableHttpResponse response = factory.create().files()
				.upload(bannerContainerName, imageFileName, inputStream, true)
				.executeWithoutHandler();
		HttpClientUtils.closeQuietly(response);

		String username = Utility.getUsername();

		GnbMenuBannerEntity entity = new GnbMenuBannerEntity();
		entity.setMenuBannerTypeId(bannerTypeId);
		entity.setImageFileName(imageFileName);
		entity.setTargetUrl(targetUrl);
		entity.setActive(true);
		entity.setCreatedOn(LocalDateTime.now());
		entity.setCreatedBy(username);
		entity.setModifiedOn(LocalDateTime.now());
		entity.setModifiedBy(username);
		gnbMenuBannerEntityRepository.save(entity);

		typeEntity.getBanners().add(entity);

		return GnbBannerResponse.convertFrom(entity, bannerImageUrl);
	}

	@Override
	public GnbBannerResponse modifyBanner(int bannerTypeId, int bannerId, String imageFileName, InputStream inputStream, String targetUrl) {
		GnbMenuBannerTypeEntity typeEntity = gnbMenuBannerTypeEntityRepository.getOneFromId(bannerTypeId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid banner type ID."));
		GnbMenuBannerEntity bannerEntity = typeEntity.getBanners().stream()
				.filter(b -> b.getMenuBannerId() == bannerId)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid banner ID."));
		String previousFileName = bannerEntity.getImageFileName();

		CloseableHttpResponse uploadResponse = factory.create().files()
				.upload(bannerContainerName, imageFileName, inputStream, true)
				.executeWithoutHandler();
		HttpClientUtils.closeQuietly(uploadResponse);

		bannerEntity.setImageFileName(imageFileName);
		bannerEntity.setTargetUrl(targetUrl);
		bannerEntity.setModifiedOn(LocalDateTime.now());
		bannerEntity.setModifiedBy(Utility.getUsername());
		gnbMenuBannerEntityRepository.save(bannerEntity);

		CloseableHttpResponse deleteResponse = factory.create().files()
				.delete(bannerContainerName, previousFileName)
				.executeWithoutHandler();
		HttpClientUtils.closeQuietly(deleteResponse);

		return GnbBannerResponse.convertFrom(bannerEntity, bannerImageUrl);
	}

	@Override
	public GnbBannerResponse modifyTargetUrl(int bannerTypeId, int bannerId, String targetUrl) {
		GnbMenuBannerTypeEntity typeEntity = gnbMenuBannerTypeEntityRepository.getOneFromId(bannerTypeId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid banner type ID."));
		GnbMenuBannerEntity bannerEntity = typeEntity.getBanners().stream()
				.filter(b -> b.getMenuBannerId() == bannerId)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid banner ID."));

		bannerEntity.setTargetUrl(targetUrl);
		bannerEntity.setModifiedOn(LocalDateTime.now());
		bannerEntity.setModifiedBy(Utility.getUsername());
		gnbMenuBannerEntityRepository.save(bannerEntity);

		return GnbBannerResponse.convertFrom(bannerEntity, bannerImageUrl);
	}
}
