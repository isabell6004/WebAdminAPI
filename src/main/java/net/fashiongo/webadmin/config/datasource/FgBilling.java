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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"net.fashiongo.webadmin.dao.billing"},
        entityManagerFactoryRef = "billingEntityManager",
        transactionManagerRef = "billingTransactionManager"
)
public class FgBilling {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource5")
    public DataSourceProperties billingDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "billingDataSource")
    @ConfigurationProperties(prefix = "spring.datasource5.hikari")
    public DataSource billingDataSource() {
        return billingDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "billingEntityManager")
    public LocalContainerEntityManagerFactoryBean billingEntityManager(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("billingDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("net.fashiongo.webadmin.model.billing").build();
    }

    @Bean
    public PlatformTransactionManager billingTransactionManager(
            @Qualifier("billingEntityManager") EntityManagerFactory billingEntityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(billingEntityManager);
        return transactionManager;
    }

    @Bean(name="billingJdbcTemplate")
    public JdbcTemplate billingJdbcTemplate (
            @Qualifier("billingDataSource") DataSource dataSource ) {
        return new JdbcTemplate(dataSource);
    }
}
