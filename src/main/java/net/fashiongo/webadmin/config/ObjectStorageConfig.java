package net.fashiongo.webadmin.config;

import net.fashiongo.webadmin.support.storage.SwiftApiCallFactory;
import net.fashiongo.webadmin.support.storage.SwiftAuth;
import net.fashiongo.webadmin.support.storage.SwiftProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SwiftProperties.class)
public class ObjectStorageConfig {

	@Bean
	public SwiftAuth swiftAuth(SwiftProperties properties) {
		return new SwiftAuth(properties);
	}

	@Bean
	public SwiftApiCallFactory swiftApiCallFactory(SwiftProperties properties) {
		return new SwiftApiCallFactory(swiftAuth(properties),properties);
	}

}
