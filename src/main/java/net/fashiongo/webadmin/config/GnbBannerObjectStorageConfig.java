package net.fashiongo.webadmin.config;


import net.fashiongo.webadmin.support.storage.GnbBannerSwiftProperties;
import net.fashiongo.webadmin.support.storage.SwiftApiCallFactory;
import net.fashiongo.webadmin.support.storage.SwiftAuth;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GnbBannerSwiftProperties.class)
public class GnbBannerObjectStorageConfig {

	@Bean
	public SwiftAuth gnbBannerSwiftAuth(GnbBannerSwiftProperties properties) {
		return new SwiftAuth(properties);
	}

	@Bean
	public SwiftApiCallFactory gnbBannerSwiftApiCallFactory(GnbBannerSwiftProperties properties) {
		return new SwiftApiCallFactory(gnbBannerSwiftAuth(properties),properties);
	}

}
