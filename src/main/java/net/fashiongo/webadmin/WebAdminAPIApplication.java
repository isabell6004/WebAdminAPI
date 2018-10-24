package net.fashiongo.webadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan({"net.fashiongo.webadmin","net.fashiongo.common.dal"})
public class WebAdminAPIApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebAdminAPIApplication.class, args);
	}
}