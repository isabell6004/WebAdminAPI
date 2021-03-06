package net.fashiongo.webadmin.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import net.fashiongo.common.dal.JdbcHelper;

@Configuration
public class ApiConfiguration {

	@Bean
	public JdbcHelper jdbcHelper(JdbcTemplate jdbcTemplate) {
		return new JdbcHelper(jdbcTemplate);
	}

	@Bean
	public JdbcHelper jdbcHelperPhotoStudio(@Qualifier("photostudioJdbcTemplate") JdbcTemplate jdbcTemplate) {
		return new JdbcHelper(jdbcTemplate);
	}

	@Bean
	public JdbcHelper jdbcHelperFgPay(@Qualifier("fgpayJdbcTemplate") JdbcTemplate jdbcTemplate) {
		return new JdbcHelper(jdbcTemplate);
	}

	@Bean
	public JdbcHelper jdbcHelperFgEm(@Qualifier("fgemJdbcTemplate") JdbcTemplate jdbcTemplate) {
		return new JdbcHelper(jdbcTemplate);
	}

	@Bean
	public JdbcHelper jdbcHelperFgBilling(@Qualifier("billingJdbcTemplate") JdbcTemplate jdbcTemplate) {
		return new JdbcHelper(jdbcTemplate);
	}

}
