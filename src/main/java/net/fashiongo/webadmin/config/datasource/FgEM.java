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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * 
 * @author Incheol Jung
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "net.fashiongo.webadmin.dao.fgem",
		entityManagerFactoryRef = "fgemEntityManager",
		transactionManagerRef = "fgemTransactionManager"
)
public class FgEM {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource3")
    public DataSourceProperties fgemDataSourceProperties() {
        return new DataSourceProperties();
    }

	@Bean(name = "fgemDataSource")
	@ConfigurationProperties(prefix="spring.datasource3.dbcp2")
	public DataSource fgemDataSource() {
		return fgemDataSourceProperties().initializeDataSourceBuilder().build();
	}
	
	@Bean(name="fgemEntityManager")
	public LocalContainerEntityManagerFactoryBean fgemEntityManager(
			EntityManagerFactoryBuilder builder, 
			@Qualifier("fgemDataSource") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages("net.fashiongo.webadmin.model.fgem")
				.build();
	}
	
	@Bean
	public PlatformTransactionManager fgemTransactionManager(@Qualifier("fgemEntityManager") EntityManagerFactory fgemEntityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(fgemEntityManager);
		return transactionManager;
	}

	@Bean(name="fgemJdbcTemplate")
    public JdbcTemplate fgemJdbcTemplate (
        @Qualifier("fgemDataSource") DataSource dataSource ) {
        return new JdbcTemplate(dataSource);
    }

}
