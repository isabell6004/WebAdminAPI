package net.fashiongo.webadmin.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CouponStorageProperties.class)
public class CouponStorageConfig {
}
