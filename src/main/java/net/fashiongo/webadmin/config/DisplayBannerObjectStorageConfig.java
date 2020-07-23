package net.fashiongo.webadmin.config;


import net.fashiongo.webadmin.support.storage.DisplayBannerSwiftProperties;
import net.fashiongo.webadmin.support.storage.SwiftApiCallFactory;
import net.fashiongo.webadmin.support.storage.SwiftAuth;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DisplayBannerSwiftProperties.class)
public class DisplayBannerObjectStorageConfig {

	@Bean
	public SwiftAuth displayBannerSwiftAuth(DisplayBannerSwiftProperties properties) {
		return new SwiftAuth(properties);
	}

	@Bean(name = "displayBannerSwiftApiCallFactory")
	public SwiftApiCallFactory displayBannerSwiftApiCallFactory(DisplayBannerSwiftProperties properties) {
		return new SwiftApiCallFactory(displayBannerSwiftAuth(properties),properties);
	}

}
