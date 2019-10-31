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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class GnbBannerServiceImpl implements GnbBannerService {

	private final GnbMenuBannerTypeEntityRepository gnbMenuBannerTypeEntityRepository;

	private final GnbMenuBannerEntityRepository gnbMenuBannerEntityRepository;

	private final SwiftApiCallFactory factory;

	private final String bannerImageUrl;

	private final String rootContainer;

	private final String bannerDirectory;

	@Autowired
	public GnbBannerServiceImpl(GnbMenuBannerTypeEntityRepository gnbMenuBannerTypeEntityRepository,
								GnbMenuBannerEntityRepository gnbMenuBannerEntityRepository,
								@Qualifier("gnbBannerSwiftApiCallFactory") SwiftApiCallFactory factory,
								@Value("${gnb.banner.image.storage.object-storage.api-url}") String objectStorageUrl,
								@Value("${gnb.banner.image.storage.object-storage.account}") String objectStorageAuth,
								@Value("${gnb.banner.image.storage.root-container}") String rootContainer,
								@Value("${gnb.banner.image.storage.directory}") String bannerDirectory) {
		this.gnbMenuBannerTypeEntityRepository = gnbMenuBannerTypeEntityRepository;
		this.gnbMenuBannerEntityRepository = gnbMenuBannerEntityRepository;
		this.rootContainer = rootContainer;
		this.bannerDirectory = bannerDirectory;
		this.bannerImageUrl = objectStorageUrl + objectStorageAuth + "/" + rootContainer + "/" + bannerDirectory;
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

		uploadBannerFile(imageFileName, inputStream);

		String userName = Utility.getUsername();
		LocalDateTime now = LocalDateTime.now();

		GnbMenuBannerEntity entity = new GnbMenuBannerEntity();
		entity.setMenuBannerTypeId(bannerTypeId);
		entity.setImageFileName(imageFileName);
		entity.setTargetUrl(targetUrl);
		entity.setActive(true);
		entity.setCreatedOn(now);
		entity.setCreatedBy(userName);
		entity.setModifiedOn(now);
		entity.setModifiedBy(userName);
		gnbMenuBannerEntityRepository.save(entity);

		typeEntity.getBanners().add(entity);
		modifyUpdatingDataOfBannerType(typeEntity, userName, now);

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

		uploadBannerFile(imageFileName, inputStream);

		String userName = Utility.getUsername();
		LocalDateTime now = LocalDateTime.now();

		bannerEntity.setImageFileName(imageFileName);
		bannerEntity.setTargetUrl(targetUrl);
		bannerEntity.setModifiedOn(now);
		bannerEntity.setModifiedBy(userName);
		gnbMenuBannerEntityRepository.save(bannerEntity);
		modifyUpdatingDataOfBannerType(typeEntity, userName, now);

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

		String userName = Utility.getUsername();
		LocalDateTime now = LocalDateTime.now();

		bannerEntity.setTargetUrl(targetUrl);
		bannerEntity.setModifiedOn(now);
		bannerEntity.setModifiedBy(userName);
		gnbMenuBannerEntityRepository.save(bannerEntity);

		modifyUpdatingDataOfBannerType(typeEntity, userName, now);

		return GnbBannerResponse.convertFrom(bannerEntity, bannerImageUrl);
	}

	@Override
	public GnbBannerResponse modifyActivity(int bannerTypeId, int bannerId, boolean isActive) {
		GnbMenuBannerTypeEntity typeEntity = gnbMenuBannerTypeEntityRepository.getOneFromId(bannerTypeId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid banner type ID."));
		GnbMenuBannerEntity bannerEntity = typeEntity.getBanners().stream()
				.filter(b -> b.getMenuBannerId() == bannerId)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid banner ID."));

		String userName = Utility.getUsername();
		LocalDateTime now = LocalDateTime.now();

		bannerEntity.setActive(isActive);
		bannerEntity.setModifiedOn(now);
		bannerEntity.setModifiedBy(userName);
		gnbMenuBannerEntityRepository.save(bannerEntity);
		modifyUpdatingDataOfBannerType(typeEntity, userName, now);

		return GnbBannerResponse.convertFrom(bannerEntity, bannerImageUrl);
	}

	@Override
	public int removeBanner(int bannerTypeId, int bannerId) {
		GnbMenuBannerTypeEntity typeEntity = gnbMenuBannerTypeEntityRepository.getOneFromId(bannerTypeId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid banner type ID."));
		GnbMenuBannerEntity bannerEntity = typeEntity.getBanners().stream()
				.filter(b -> b.getMenuBannerId() == bannerId)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid banner ID."));
		String removedFileName = bannerEntity.getImageFileName();

		gnbMenuBannerEntityRepository.delete(bannerEntity);
		typeEntity.setBanners(typeEntity.getBanners().stream().filter(b -> b.getMenuBannerId() != bannerId).collect(Collectors.toList()));
		modifyUpdatingDataOfBannerType(typeEntity, Utility.getUsername(),LocalDateTime.now());

		return bannerId;
	}

	private void uploadBannerFile(String fileName, InputStream inputStream) {
		if (!isValidFileName(fileName)) {
			throw new IllegalArgumentException("Invalid file name.");
		}
		CloseableHttpResponse uploadResponse = factory.create().files()
				.upload(rootContainer, getBannerFileNameWithPath(fileName), inputStream, true)
				.executeWithoutHandler();
		HttpClientUtils.closeQuietly(uploadResponse);
	}

	private boolean isValidFileName(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return false;
		}
		Pattern validFileNamePattern = Pattern.compile("[a-zA-Z0-9\\-_.]+");
		Matcher matcher = validFileNamePattern.matcher(fileName);
		return matcher.matches();
	}

	// Comment out to prevent DB rollback issue
//	private void deleteBannerFile(String fileName) {
//		CloseableHttpResponse deleteResponse = factory.create().files()
//				.delete(rootContainer, getBannerFileNameWithPath(fileName))
//				.executeWithoutHandler();
//		HttpClientUtils.closeQuietly(deleteResponse);
//	}

	private String getBannerFileNameWithPath(String fileName) {
		return this.bannerDirectory + "/" + fileName;
	}

	private void modifyUpdatingDataOfBannerType(GnbMenuBannerTypeEntity typeEntity, String userName, LocalDateTime now) {
		typeEntity.setModifiedOn(now);
		typeEntity.setModifiedBy(userName);
		gnbMenuBannerTypeEntityRepository.save(typeEntity);
	}
}
