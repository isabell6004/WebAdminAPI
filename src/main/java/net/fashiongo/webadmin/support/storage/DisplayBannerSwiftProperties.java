package net.fashiongo.webadmin.support.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "display.banner.image.storage.object-storage")
public class DisplayBannerSwiftProperties implements SwiftPropertyInterface {
	private String account;

	private String apiUrl;

	private String tenantId;

	private String username;

	private String password;

	private String authApiUrl;
}
