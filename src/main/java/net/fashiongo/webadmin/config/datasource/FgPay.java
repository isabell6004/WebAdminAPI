/**
 * 
 */
package net.fashiongo.webadmin.config.datasource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Brian
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "net.fashiongo.webadmin.dao.fgpay",
		entityManagerFactoryRef = "fgpayEntityManager",
		transactionManagerRef = "fgpayTransactionManager"
)
public class FgPay {
	@Bean(name = "fgpayDataSource")
	@ConfigurationProperties(prefix="spring.datasource2")
	public DataSource fgpayDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="fgpayEntityManager")
	public LocalContainerEntityManagerFactoryBean fgpayEntityManager(EntityManagerFactoryBuilder builder, @Qualifier("fgpayDataSource") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages("net.fashiongo.webadmin.model.fgpay")
				.build();
	}
	
	@Primary
	@Bean
	public PlatformTransactionManager fgpayTransactionManager(@Qualifier("fgpayEntityManager") EntityManagerFactory fgpayEntityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(fgpayEntityManager);
		return transactionManager;
	}
}
