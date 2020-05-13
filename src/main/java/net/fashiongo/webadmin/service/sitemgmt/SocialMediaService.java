package net.fashiongo.webadmin.service.sitemgmt;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.common.dal.JdbcHelper;
import net.fashiongo.webadmin.data.entity.primary.sitemgmt.SocialMedia;
import net.fashiongo.webadmin.data.model.sitemgmt.SocialMediaParameter;
import net.fashiongo.webadmin.data.repository.primary.sitemgmt.SocialMediaRepository;
import net.fashiongo.webadmin.data.repository.primary.vendor.ListSocialMediaEntityRepository;
import net.fashiongo.webadmin.exception.NotFoundSitemgmtException;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class SocialMediaService {

    private SocialMediaRepository socialMediaRepository;

    private SocialMediaNewService socialMediaNewService;

    private ListSocialMediaEntityRepository listSocialMediaEntityRepository;

    public SocialMediaService(JdbcHelper jdbcHelper,
                              SocialMediaNewService socialMediaNewService,
                              SocialMediaRepository socialMediaRepository,
                              ListSocialMediaEntityRepository listSocialMediaEntityRepository
    ) {
        this.socialMediaNewService = socialMediaNewService;
        this.socialMediaRepository = socialMediaRepository;
        this.listSocialMediaEntityRepository = listSocialMediaEntityRepository;
    }

	public List<SocialMedia> getSocialMedias() {
		return socialMediaRepository.findAllByOrderBySocialMediaIdAsc();
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public boolean deleteSocialMedias(String socialMediaIds) {

        List<Integer> socilMediaIdList = Optional.ofNullable(socialMediaIds)
                .filter(s -> org.springframework.util.StringUtils.hasLength(s))
                .map(s -> Arrays.asList(s.split(",")))
                .orElse(new ArrayList<String>())
                .stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        if(socilMediaIdList.size() > 0) {
            listSocialMediaEntityRepository.deleteByIds(socilMediaIdList);
            WebAdminLoginUser userInfo = Utility.getUserInfo();
            Boolean fashionGoApiResult = socialMediaNewService.delete(socilMediaIdList, userInfo.getUserId(), userInfo.getUsername());
            if(!fashionGoApiResult) {
                log.error("fail to delete the social media code. : {}", socialMediaIds);
                throw new NotFoundSitemgmtException("fail to delete the social media code. : " + socialMediaIds);
            }
            return true;
        }
        return false;
	}

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public Boolean saveSocialMedia(SocialMediaParameter request) {
        WebAdminLoginUser userInfo = Utility.getUserInfo();
		try {
            Boolean fashionGoApiResult;
            
            String fileName = validateFileName(request.getIcon());
            
            if (fileName == null) {
            	return false;
            }
            else {
	            if(request.getSocialMediaId() == null || request.getSocialMediaId() == 0) {
			        SocialMedia socialMedia = SocialMedia.create(request);
			        socialMedia.setIcon(fileName);
	                socialMediaRepository.save(socialMedia);
	                fashionGoApiResult = socialMediaNewService.register(socialMedia, userInfo.getUserId(), userInfo.getUsername());
	            } else {
	                SocialMedia socialMedia = socialMediaRepository.findById(request.getSocialMediaId()).orElseThrow(NotFoundSitemgmtException::new);
	                socialMedia.update(request);
	                socialMedia.setIcon(fileName);
	                socialMediaRepository.save(socialMedia);
	                fashionGoApiResult = socialMediaNewService.update(socialMedia, userInfo.getUserId(), userInfo.getUsername());
	            }
	            if(!fashionGoApiResult) {
	                log.error("fail to save the social media code. : {}", request.toString());
	                throw new NotFoundSitemgmtException("fail to save the social media code. : " + request.toString());
	            }
	            return true;
            }
		} catch (Exception e) {
			return false;
		}
	}
    
	private String validateFileName(String fileName) {
		if(fileName == null) {
			return null;
		}
		Set<String> extensionSet = Stream.of(".jpg", ".gif", ".png", ".pdf", ".xls", ".xlsx", ".doc", ".docx")
				.collect(Collectors.toCollection(HashSet::new));

		String fileExtension = fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
		if(!extensionSet.contains(fileExtension)) {
			return null;
		}

		String[] fileNameBlackList = {"/", "\\", "%0", ";"};
		for(String wrongName : fileNameBlackList) {
			if(fileName.indexOf(wrongName) != -1) {
				return null;
			}
		}

		return fileName;
	}    
	
}
