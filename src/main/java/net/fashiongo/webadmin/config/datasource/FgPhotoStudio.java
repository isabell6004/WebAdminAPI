package net.fashiongo.webadmin.config.datasource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Andy
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "net.fashiongo.webadmin.dao.photostudio",
		entityManagerFactoryRef = "photostudioEntityManager",
		transactionManagerRef = "photostudioTransactionManager")
public class FgPhotoStudio {
	@Bean(name = "photostudioDataSource")
	@ConfigurationProperties(prefix = "spring.datasource3")
	public DataSource photostudioDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "photostudioEntityManager")
	public LocalContainerEntityManagerFactoryBean photostudioEntityManager(EntityManagerFactoryBuilder builder,
			@Qualifier("photostudioDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("net.fashiongo.webadmin.model.photostudio").build();
	}

	@Bean
	public PlatformTransactionManager photostudioTransactionManager(
			@Qualifier("photostudioEntityManager") EntityManagerFactory photostudioEntityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(photostudioEntityManager);
		return transactionManager;
	}

	@Bean(name="photostudioJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate (
        @Qualifier("photostudioDataSource") DataSource dataSource ) {
        return new JdbcTemplate(dataSource);
    }
}
