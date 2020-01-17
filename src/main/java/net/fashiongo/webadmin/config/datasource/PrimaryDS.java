/**
 * 
 */
package net.fashiongo.webadmin.config.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author Brian
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = {"net.fashiongo.webadmin.dao.primary", "net.fashiongo.webadmin.data.repository.primary", "net.fashiongo.common.data.repository"},
		entityManagerFactoryRef = "primaryEntityManager",
		transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDS {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

	@Primary
	@Bean(name = "primaryDataSource")
	@ConfigurationProperties(prefix="spring.datasource.dbcp2")
	public DataSource primaryDataSource() {
		return firstDataSourceProperties().initializeDataSourceBuilder().build();
	}
	
	@Primary
	@Bean(name="primaryEntityManager")
	public LocalContainerEntityManagerFactoryBean primaryEntityManager(EntityManagerFactoryBuilder builder, @Qualifier("primaryDataSource") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages("net.fashiongo.webadmin.model.primary","net.fashiongo.webadmin.data.entity.primary","net.fashiongo.common.data.model.entity")
				.persistenceUnit("primaryEntityManager")
				.build();
	}
	
	@Primary
	@Bean(name = "primaryTransactionManager")
	public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryEntityManager") EntityManagerFactory primaryEntityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(primaryEntityManager);
		return transactionManager;
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
