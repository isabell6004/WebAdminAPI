package net.fashiongo.webadmin.config;

import net.fashiongo.webadmin.support.storage.CollectionBannerSwiftProperties;
import net.fashiongo.webadmin.support.storage.SwiftApiCallFactory;
import net.fashiongo.webadmin.support.storage.SwiftAuth;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CollectionBannerSwiftProperties.class)
public class CollectionBannerObjectStorageConfig {

    @Bean
    public SwiftAuth collectionBannerSwiftAuth(CollectionBannerSwiftProperties properties) { return new SwiftAuth(properties); }

    @Bean(name = "collectionBannerSwiftApiCallFactory")
    public SwiftApiCallFactory collectionBannerSwiftApiCallFactory(CollectionBannerSwiftProperties properties) {
        return new SwiftApiCallFactory(collectionBannerSwiftAuth(properties), properties);
    }
}
