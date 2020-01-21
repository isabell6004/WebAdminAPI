package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.repository.primary.vendor.ListSocialMediaEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RenewalSocialMediaService {

	@Autowired
	private ListSocialMediaEntityRepository listSocialMediaEntityRepository;

	public boolean deleteSocialMedias(String socialMediaIds) {
		List<Integer> socilMediaIdList = Optional.ofNullable(socialMediaIds)
				.filter(s -> StringUtils.hasLength(s))
				.map(s -> Arrays.asList(s.split(",")))
				.orElse(new ArrayList<String>())
				.stream()
				.map(Integer::valueOf)
				.collect(Collectors.toList());

		if(socilMediaIdList.size() > 0) {
			listSocialMediaEntityRepository.deleteByIds(socilMediaIdList);
		}

		return true;
	}
}
