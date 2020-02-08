package net.fashiongo.webadmin.service.sitemgmt;

import net.fashiongo.webadmin.data.entity.primary.sitemgmt.SocialMedia;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SocialMediaNewService {

    Boolean delete(List<Integer> delIds, Integer requestUserId, String requestUserName);

    Boolean register(SocialMedia socialMedia, Integer requestUserId, String requestUserName);

    Boolean update(SocialMedia socialMedia, Integer requestUserId, String requestUserName);
}
