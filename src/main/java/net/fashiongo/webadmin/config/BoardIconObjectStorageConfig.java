package net.fashiongo.webadmin.config;

import net.fashiongo.webadmin.support.storage.BoardIconSwiftProperties;
import net.fashiongo.webadmin.support.storage.SwiftApiCallFactory;
import net.fashiongo.webadmin.support.storage.SwiftAuth;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BoardIconSwiftProperties.class)
public class BoardIconObjectStorageConfig {

    @Bean
    public SwiftAuth boardIconSwiftAuth(BoardIconSwiftProperties properties) {
        return new SwiftAuth(properties);
    }

    @Bean(name = "boardIconSwiftApiCallFactory")
    public SwiftApiCallFactory boardIconSwiftApiCallFactory(BoardIconSwiftProperties properties) {
        return new SwiftApiCallFactory(boardIconSwiftAuth(properties), properties);
    }
}
