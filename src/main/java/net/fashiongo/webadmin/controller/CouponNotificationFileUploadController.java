package net.fashiongo.webadmin.controller;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.service.CouponNotificationFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/coupon/notification")
@Slf4j
public class CouponNotificationFileUploadController {

	@Autowired
	private CouponNotificationFileUploadService service;

	@PostMapping(path = "/{notificationId}/upload/email")
	public JsonResponse<Boolean> uploadEmailListFile(@PathVariable(value = "notificationId") Integer notificationId, @RequestPart(name = "files", required = false) List<MultipartFile> files) {
		if(files.size() > 0) {
			try {
				MultipartFile multipartFile = files.get(0);
				String originalFilename = multipartFile.getOriginalFilename();
				InputStream inputStream = multipartFile.getInputStream();
				service.uploadEmail(notificationId,originalFilename,inputStream);
				return new JsonResponse(true,"",true);
			} catch (Exception e) {
				log.warn(e.getMessage(),e);
			}
		}

		return new JsonResponse(false,"Unknown File Parameter",false);
	}

	@PostMapping(path = "/{notificationId}/upload/image")
	public JsonResponse<Boolean> uploadImageFile(@PathVariable(value = "notificationId") Integer notificationId, @RequestPart(name = "files", required = false) List<MultipartFile> files) {
		if(files.size() > 0) {
			try {
				MultipartFile multipartFile = files.get(0);
				String originalFilename = multipartFile.getOriginalFilename();
				InputStream inputStream = multipartFile.getInputStream();
				service.uploadImage(notificationId,originalFilename,inputStream);
				return new JsonResponse(true,"",true);
			} catch (Exception e) {
				log.warn(e.getMessage(),e);
			}
		}

		return new JsonResponse(false,"Unknown File Parameter",false);
	}
}
