package net.fashiongo.webadmin.service;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.config.CouponStorageProperties;
import net.fashiongo.webadmin.data.repository.primary.CouponNotificationEntityRepository;
import net.fashiongo.webadmin.support.FileNameUtils;
import net.fashiongo.webadmin.support.storage.SwiftApiCallFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
public class CouponNotificationFileUploadService {

	@Autowired
	@Qualifier("swiftApiCallFactory")
	private SwiftApiCallFactory factory;

	@Autowired
	private CouponStorageProperties properties;

	@Autowired
	private CouponNotificationEntityRepository couponNotificationEntityRepository;

	public void uploadEmail(int couponNotificationId,String fileName,InputStream inputStream) {
		String containerName = properties.getRootNamePrefix() + properties.getRootEmailName();
		String objectPath = "" + couponNotificationId + "/" + fileName;

		fileUpload(containerName,objectPath,inputStream);

		String filePathAndName = FileNameUtils.updateOBSPrefixNotificationSavedFile(fileName);
		long updatedCount = couponNotificationEntityRepository.updateTargetFileName(couponNotificationId,filePathAndName);

		log.debug("updated count : {}",updatedCount);
	}

	private void fileUpload(String containerName, String objectPath, InputStream inputStream) {
		CloseableHttpResponse response = factory.create().files()
				.upload(containerName, objectPath, inputStream, true)
				.executeWithoutHandler();

		HttpClientUtils.closeQuietly(
				response
		);
	}

	public void uploadImage(int couponNotificationId,String fileName,InputStream inputStream) {
		String containerName = properties.getRootNamePrefix() + properties.getRootImageName();
		String objectPath = "" + couponNotificationId + "/" + fileName;

		fileUpload(containerName,objectPath,inputStream);

		String filePathAndName = FileNameUtils.updateOBSPrefixNotificationSavedFile(fileName);

		long updatedCount = couponNotificationEntityRepository.updateImageFileName(couponNotificationId, filePathAndName);

		log.debug("updated count : {}",updatedCount);
	}
}
