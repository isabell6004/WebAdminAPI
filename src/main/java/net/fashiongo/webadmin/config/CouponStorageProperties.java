package net.fashiongo.webadmin.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "coupon.notification.storage")
public class CouponStorageProperties {

	private String rootEmailName;
	private String rootImageName;
	private String rootNamePrefix;
}
